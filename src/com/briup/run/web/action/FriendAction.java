package com.briup.run.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.exception.MemberServiceException;
import com.briup.run.common.exception.MessengerServiceException;
import com.briup.run.common.util.BeanFactory;
import com.briup.run.service.IMemberService;
import com.briup.run.service.IMessengerService;
import com.opensymphony.xwork2.ActionSupport;
public class FriendAction extends ActionSupport implements ServletRequestAware{
	//request
	private HttpServletRequest request;
	//IMemberService的service接口
	private IMemberService memberService = (IMemberService) BeanFactory.getBean(BeanFactory.MEMBERSERVICE);
	//IMessengerService的service接口
	private IMessengerService messengerService = (IMessengerService) BeanFactory.getBean(BeanFactory.MESSENGERSERVICE);
	//好友列表
	private List<Memberinfo> memberList = new ArrayList<Memberinfo>();
	
	//获取全体memberinfo列表
	public String listMembers() throws Exception {
		//遍历所有的会员
		setMemberList(messengerService.findFriends("unlimited","unlimited","unlimited"));
		return SUCCESS;
	}
	
	//添加朋友
	//接受页面传过来的friendName
	private String friendname;
	private String msg;
	public String addFriend(){
		HttpSession session = request.getSession();
		//获取session中的memberinfo
		Memberinfo member = (Memberinfo) session.getAttribute("member");
		try {
			memberService.saveOrUpdate(member.getNickname(), friendname);
			msg="添加好友成功！";
			return SUCCESS;
		} catch (MemberServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			setMsg(e.getMessage());
			return ERROR;
		}
	}
	
	//显示好友列表
	private List<Memberinfo> friendList;
	public String listFriends(){
		HttpSession session = request.getSession();
		//获取session中的member
		Memberinfo member = (Memberinfo) session.getAttribute("member");
		//列出好友名单
		try {
			friendList = memberService.listFriend(member.getNickname());
			return SUCCESS;
		} catch (MemberServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	
	//好友速配
	private String age;
	private String gender;
	private String provinceCity;
	
	public String matchFriend(){
		System.out.println("输出传入的条件"+age+gender+provinceCity);
		try {
			setMemberList(messengerService.findFriends(age,gender,provinceCity));
			return SUCCESS;
		} catch (MessengerServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	
	//快速匹配
	public String randMatchFriend(){
		//获取session中的memberinfo
		HttpSession session = request.getSession();
		Memberinfo member = (Memberinfo) session.getAttribute("member");
		//
		try {
			
			System.out.println("初始化memberList");
			/*memberList = messengerService.findFriends("unlimited","unlimited","unlimited");*/
			memberList.clear();
			memberList.add(messengerService.findNotFriend(member.getNickname()));
			return SUCCESS;
		} catch (MessengerServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}

	//显示黑名单
	private List<Memberinfo> blackList;
	public String blackList(){
		HttpSession session = request.getSession();
		//获取session中的member
		Memberinfo member = (Memberinfo) session.getAttribute("member");
		//列出黑名单
		try {
			System.out.println("列出黑名单");
			blackList = memberService.listBlack(member.getNickname());
			return SUCCESS;
		} catch (MemberServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		
	}
	
	//移到黑名单
	private String blackname;
	public String moveToBlack (){
		HttpSession session = request.getSession();
		//获取session中的member
		Memberinfo member = (Memberinfo) session.getAttribute("member");
		try {
			System.out.println("移到黑名单");
			memberService.moveToBlack(member.getNickname(), blackname);
			//从好友中删除
			memberService.deleleFriend(member.getNickname(), blackname);
			msg = "成功将用户："+getBlackname()+"移到黑名单";
			return SUCCESS;
		} catch (MemberServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	
	//删除好友
	public String deleteBuddy(){
		HttpSession session = request.getSession();
		//获取session中的member
		Memberinfo member = (Memberinfo) session.getAttribute("member");
		//删除好友
		try {
			memberService.deleleFriend(member.getNickname(), friendname);
			return SUCCESS;
		} catch (MemberServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	
	//移出黑名单
	public String deleleBlack(){
		HttpSession session = request.getSession();
		//获取session中的member
		Memberinfo member = (Memberinfo) session.getAttribute("member");
		//删除黑名单
		try {
			memberService.deleleBlack(member.getNickname(), blackname);
			memberService.saveOrUpdate(member.getNickname(), blackname);
			msg = "成功将用户："+getBlackname()+"移到好友名单";
			return SUCCESS;
		} catch (MemberServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	public List<Memberinfo> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Memberinfo> memberList) {
		this.memberList = memberList;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getFriendname() {
		return friendname;
	}

	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public List<Memberinfo> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<Memberinfo> friendList) {
		this.friendList = friendList;
	}



	public String getAge() {
		return age;
	}



	public void setAge(String age) {
		this.age = age;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getProvinceCity() {
		return provinceCity;
	}



	public void setProvinceCity(String provinceCity) {
		this.provinceCity = provinceCity;
	}

	public List<Memberinfo> getBlackList() {
		return blackList;
	}

	public void setBlackList(List<Memberinfo> blackList) {
		this.blackList = blackList;
	}

	public String getBlackname() {
		return blackname;
	}

	public void setBlackname(String blackname) {
		this.blackname = blackname;
	}
	
}

