package com.briup.run.common.util;

import com.briup.run.dao.IMemberDao;
import com.briup.run.dao.IMessengerDao;
import com.briup.run.dao.impl.MemberDaoImpl;
import com.briup.run.dao.impl.MessengerDaoImpl;
import com.briup.run.service.IMemberService;
import com.briup.run.service.IMessengerService;
import com.briup.run.service.impl.MemberServiceImpl;
import com.briup.run.service.impl.MessengerServiceImpl;

public class BeanFactory {
	public static String MEMBERDAO = "memberDao";
	public static String MEMBERSERVICE = "memberService";
	public static String MESSENGERDAO = "messengerDao";
	public static String MESSENGERSERVICE = "messengerService";

	private static IMemberDao memberDao;
	private static IMemberService memberService;
	private static IMessengerDao messengerDao;
	private static IMessengerService messengerService;

	public static Object getBean(String beanName) {
		if (beanName.equals(MEMBERDAO)) {
			memberDao = getMemberDao();
			return memberDao;
		}
		if (beanName.equals(MEMBERSERVICE)) {
			memberService = getMemberService();
			return memberService;
		}
		if (beanName.equals(MESSENGERDAO)) {
			messengerDao = getMessengerDao();
			return messengerDao;
		}
		if (beanName.equals(MESSENGERSERVICE)) {
			messengerService = getMessengerService();
			return messengerService;
		}
		
		return null;
	}
	
	synchronized private static IMemberDao getMemberDao() {
		memberDao = new MemberDaoImpl();
		return memberDao;
	}

	synchronized private static IMemberService getMemberService() {
		memberService = new MemberServiceImpl();
		return memberService;
	}

	synchronized private static IMessengerDao getMessengerDao() {
		messengerDao = new MessengerDaoImpl();
		return messengerDao;
	}

	synchronized private static IMessengerService getMessengerService() {
		messengerService = new MessengerServiceImpl();
		return messengerService;
	}
}
