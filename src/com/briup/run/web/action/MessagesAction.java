package com.briup.run.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.bean.Messagerecord;
import com.briup.run.common.exception.MessengerServiceException;
import com.briup.run.common.util.BeanFactory;
import com.briup.run.service.IMemberService;
import com.briup.run.service.IMessengerService;
import com.opensymphony.xwork2.ActionSupport;
public class MessagesAction extends ActionSupport implements ServletRequestAware{
	//request
	private HttpServletRequest request;
	/*//收信人
	private String receiver;
	//标题
	private String title;
	//内容
	private String content;*/
	//Messagerecord
	private Messagerecord messagerecord;
	
	//IMessengerService的service接口
	private IMessengerService messengerService = (IMessengerService) BeanFactory.getBean(BeanFactory.MESSENGERSERVICE);
	//IMemberService的service接口
	private IMemberService memberService = (IMemberService) BeanFactory.getBean(BeanFactory.MEMBERSERVICE);
	//异常信息
	private String msg;
	//发送短信
	public String sendInfo() throws Exception {
		HttpSession session = request.getSession();
		//获取session中的memberinfo
		Memberinfo member = (Memberinfo) session.getAttribute("member");
		//判断收件人是否存在
		Memberinfo memberinfo = memberService.findMemberinfoByName(messagerecord.getReceiver());
		if (memberinfo == null) {
			msg = "收件人不存在";
			return ERROR;
		}
		//设置messagerecord的属性
		//设置发件人
		messagerecord.setSender(member.getNickname());
		//设置发送时间
		messagerecord.setSenddate(new Date());
		//设置发送状态
		messagerecord.setReceiverstatus(0L);
		//设置删除信息状态
		messagerecord.setSenderstatus(0L);
		//设置阅读状态
		messagerecord.setStatus(0L);
		//存入Messagerecord
		messengerService.saveMessage(messagerecord);
		return SUCCESS;
	}
	
	//显示收件箱
	private List<Messagerecord> listMessages;
	public String listMessages(){
		HttpSession session = request.getSession();
		//获取session中的memberinfo
		Memberinfo member = (Memberinfo) session.getAttribute("member");
		
		//获取发件箱
		try {
			listMessages = messengerService.listSendMessage(member.getNickname());
			return SUCCESS;
		} catch (MessengerServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}

	public IMessengerService getMessengerService() {
		return messengerService;
	}

	public void setMessengerService(IMessengerService messengerService) {
		this.messengerService = messengerService;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.setRequest(arg0);
	}

	public IMemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(IMemberService memberService) {
		this.memberService = memberService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Messagerecord getMessagerecord() {
		return messagerecord;
	}

	public void setMessagerecord(Messagerecord messagerecord) {
		this.messagerecord = messagerecord;
	}

	public List<Messagerecord> getListMessages() {
		return listMessages;
	}

	public void setListMessages(List<Messagerecord> listMessages) {
		this.listMessages = listMessages;
	}

	
}
