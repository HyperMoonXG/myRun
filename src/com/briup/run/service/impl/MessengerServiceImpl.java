package com.briup.run.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.lang.jstl.IntegerDivideOperator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.BatcherFactory;

import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.bean.Messagerecord;
import com.briup.run.common.exception.DataAccessException;
import com.briup.run.common.exception.MessengerServiceException;
import com.briup.run.common.transaction.HibernateTransaction;
import com.briup.run.common.util.BeanFactory;
import com.briup.run.common.util.HibernateSessionFactory;
import com.briup.run.dao.IMemberDao;
import com.briup.run.dao.IMessengerDao;
import com.briup.run.dao.impl.MemberDaoImpl;
import com.briup.run.dao.impl.MessengerDaoImpl;
import com.briup.run.service.IMessengerService;

public class MessengerServiceImpl implements IMessengerService {

    private IMessengerDao messengerDao = (IMessengerDao) BeanFactory.getBean(BeanFactory.MESSENGERDAO); 
    private IMemberDao memberDao = (MemberDaoImpl) BeanFactory
    	      .getBean(BeanFactory.MEMBERDAO);
    private HibernateTransaction transaction = new HibernateTransaction();
	@Override
	public Integer findNewMessageNum(String nickname)
			throws MessengerServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Memberinfo findOneMemberinfo() throws MessengerServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Memberinfo> findFriends(String age, String gender, String city)
			throws MessengerServiceException {
		List<Memberinfo> list = null;
		try {
			System.out.println("调用dao的findFriends");
			//调用dao的findFriends
			list = messengerDao.findFriends(age, gender, city);
		} catch (DataAccessException e) {
			//按条件查找好友失败！
			System.out.println("按条件查找好友失败！");
			e.printStackTrace();
		}
		//返回符合条件的好友列表
		System.out.println("返回符合条件的好友列表");
		return list;
	}

	@Override
	public void saveMessage(Messagerecord message)
			throws MessengerServiceException {
		try {
			 //开启事务
		    transaction.beginTransaction();
			messengerDao.saveMessage(message);
			transaction.commit();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Messagerecord> listSendMessage(String senderName)
			throws MessengerServiceException {
		// TODO Auto-generated method stub
		try {
			List<Messagerecord> list = messengerDao.listSendMessage(senderName);
			
			return list;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Messagerecord> listRecieveMessage(String recieveName)
			throws MessengerServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Messagerecord findMessage(Long id) throws MessengerServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delRecieveMessage(Long id) throws MessengerServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delSendMessage(Long id) throws MessengerServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public Memberinfo findNotFriend(String selfname) throws MessengerServiceException {
		Memberinfo memberinfo = messengerDao.findNotFriend(selfname);
		return memberinfo;
		/*Session session = HibernateSessionFactory.getSession();
		//hql语句：首先查找年龄范围
		String hql = "from Memberinfo memberinfo where memberinfo.nickname !=?";
		//获取本人所有好友名字
		try {
			List<Memberinfo> list = memberDao.listFriend(selfname);
			//遍历所有好友的名字
			List<String> nickList = new ArrayList<String>();
			
			for (Memberinfo memberinfo : list) {
				nickList.add(memberinfo.getNickname());
				hql += " and memberinfo.nickname !=? ";
				System.out.println(memberinfo.getNickname());
			}
			
			//打印hql语句
			System.out.println("测试测试测试"+hql);
			//创建hql查询
			Query query = session.createQuery(hql);
			query.setString(0, selfname);
			for (int i = 1; i <= list.size(); i++) {
				//nickList.get(i);
				query.setString(i, nickList.get(i-1));
			}
			
			
			//执行查询
			List<Memberinfo> list2 = query.list();
			System.out.println("执行查询");
			//判断是否存在
			if (list2.size()==0) {
				System.out.println("似乎你已经添加了所有的会员");
		       return null;
		    }
			//获取列表范围
			int len = query.list().size();
			System.out.println("获取列表范围");
			//随机获取一个名字 
			int rand=(int)(1+Math.random()*( (len-2)-0+1));
			System.out.println("随机获取一个名字");
			//获取随机产生的用户
			Memberinfo memberinfo = list2.get(rand);
			System.out.println("获取随机产生的用户");
			//返回该用户名
			return memberinfo;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}*/
		
	}

	public IMemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(IMemberDao memberDao) {
		this.memberDao = memberDao;
	}

}
