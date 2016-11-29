package com.briup.run.web.action;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.util.BeanFactory;
import com.briup.run.service.impl.MemberServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class CheckAutoLoginAction extends ActionSupport
		implements
			ServletRequestAware {
	// nickname 用于获取cookie的用户名
	private String nickname;
	// password 用于获取cookie的密码
	private String password;
	// request
	private HttpServletRequest request;
	// MemberServiceImpl类
	private MemberServiceImpl memberServiceImpl = (MemberServiceImpl) BeanFactory
			.getBean(BeanFactory.MEMBERSERVICE);

	public String execute() throws Exception {
		// 获取浏览器中的cookie
		Cookie[] cookies = request.getCookies();
		System.out.println("获取浏览器中的cookie");
		if (cookies != null) {
			// 将cookie遍历
			for (Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();
				System.out.println(c.getValue());
				System.out.println("将cookie遍历");
				// 获取到用户名
				if (name.equals("nickname")) {
					// 解码。获取nickname
					nickname = URLDecoder.decode(value, "UTF-8");
					System.out.println("解码。获取nickname");
					
					// 判断值是否为空，空则返回ERROR
					if ("".equals(nickname)) {
						System.out.println("获取nickname失败");
						return ERROR;
					}
				}
				// 获取到密码
				if (name.equals("password")) {
					// 获取password
					password = value;
					// 判断值是否为空，空则返回ERROR
					if ("".equals(password)) {
						System.out.println("获取password失败");
						return ERROR;
					}
				}
				// 如果获取不到用户和密码
				/*else {
					System.out.println("获取不到用户和密码");
					return ERROR;
				}*/
			}
		}
		System.out.println("获取cookie成功");
		// 执行登录动作
		Memberinfo memberinfo = memberServiceImpl.login(nickname, password);
		// 判断返回的memberinfo是否为空
		if (memberinfo == null) {
			return ERROR;
		} else {
			// 把memberinfo放入session中
			request.getSession().setAttribute("member", memberinfo);
		}
		return SUCCESS;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

}
