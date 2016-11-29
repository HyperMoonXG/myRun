package com.briup.run.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.util.BeanFactory;
import com.briup.run.service.impl.MemberServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class RegisterAction extends ActionSupport implements ServletRequestAware {

	private Memberinfo memberinfo;
	private MemberServiceImpl memberServiceImpl = (MemberServiceImpl) BeanFactory.getBean(BeanFactory.MEMBERSERVICE);

	private String authCode;
	private HttpServletRequest request;
	// 验证码信息
	private String msg;
	// 验证码
	String code = "520134";

	// 验证
	public String register() {
		System.out.println(memberinfo);
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("authCode");
		if (!code.equals(authCode)) {
			msg = "验证码错误";
			return ERROR;
		}
		try {
			memberServiceImpl.registerMemberinfo(memberinfo);
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return ERROR;
		}
	}

	// 短信验证码
	public String smsverify() {
		// 阿里大鱼URL
		String url = "http://gw.api.taobao.com/router/rest";
		// appkey
		String appkey = "23487538";
		// secret
		String secret = "dc16fd5092b60f0c503fe82c03c20a66";

		// phone
		String phone = "18577203742";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("1");
		req.setSmsType("normal");
		req.setSmsFreeSignName("萱草");
		// 模板+验证码
		req.setSmsParamString("{number:'" + code + "'}");
		req.setRecNum(phone);
		req.setSmsTemplateCode("SMS_21015001");
		AlibabaAliqinFcSmsNumSendResponse rsp;
		try {
			rsp = client.execute(req);
			System.out.println(rsp.getBody());
			msg = "验证码发送成功,请注意查收";
			return SUCCESS;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = "验证码发送失败";
			return ERROR;
		}
	}

	public Memberinfo getMemberinfo() {
		return memberinfo;
	}

	public void setMemberinfo(Memberinfo memberinfo) {
		this.memberinfo = memberinfo;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

}
