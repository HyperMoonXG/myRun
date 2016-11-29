package com.briup.run.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.bean.Messagerecord;
import com.briup.run.common.bean.Pointaction;
import com.briup.run.common.exception.DataAccessException;
import com.briup.run.common.exception.MessengerServiceException;
import com.briup.run.common.util.BeanFactory;
import com.briup.run.common.util.HibernateSessionFactory;
import com.briup.run.dao.IMemberDao;
import com.briup.run.dao.IMessengerDao;

public class MessengerDaoImpl implements IMessengerDao {

	private IMemberDao memberDao = (IMemberDao) BeanFactory.getBean(BeanFactory.MEMBERDAO);
	@Override
	public Integer findNewMessageNum(String nickname)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findMemberinfoNum() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Memberinfo findOneMemberinfo(int sum) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Memberinfo> findFriends(String age, String gender, String city)
			throws DataAccessException {
		Session session = HibernateSessionFactory.getSession();
		//设置年龄的全部范围
		Long min = 0L;
		Long max = 300L;
		
		//判断是否选择年龄限制条件
		if (!"unlimited".equals(age)) {
			//如果选择了年龄限制条件，则继续判断是哪一个范围
			if ("1".equals(age)) {
				min=10L;
				max=19L;
			}
			if ("2".equals(age)) {
				min=20L;
				max=29L;
			}
			if ("3".equals(age)) {
				min=30L;
				max=39L;
			}
		}
		
		//hql语句：首先查找年龄范围
		String hql = "from Memberinfo where (age>=:min and age <=:max)";
		
		//判断是否选择性别限制条件
		if (!"unlimited".equals(gender)) {
			//添加性别查询条件
			hql += " and gender =:gender";
		}
		//判断是否选择城市限制条件
		if (!"unlimited".equals(city)) {
			//添加省份查询条件
			hql += " and provincecity =:city";
		}
		
		//打印hql语句
		//打印各属性值
		System.out.println("打印各属性值");
		System.out.println("min:" + min + "max" + max);
		System.out.println("gender：" + gender);
		System.out.println("city：" + city);
		System.out.println("没设范围时打印hql语句：" + hql);
		
		//创建hql查询
		Query query = session.createQuery(hql);
		//设置max，min（年龄范围）
		query.setLong("min", min);
		query.setLong("max", max);
		
		//判断是否选择性别限制条件
		if (!"unlimited".equals(gender)) {
			//设置性别
			query.setString("gender", gender);
		}
		//判断是否选择城市限制条件
		if (!"unlimited".equals(city)) {
			//设置性别
			query.setString("city", city);
		}
		
		/*//遍历符合条件的好友
		System.out.println("遍历符合条件的好友");
		List<Memberinfo> prinList = query.list();*/
		//打印各属性值
		System.out.println("打印各属性值");
		System.out.println("min:"+min+"max"+max);
		System.out.println("gender："+gender);
		System.out.println("city："+city);
		//打印hql语句
		System.out.println("设置范围后打印hql语句："+hql);
		
		/*
		for (Memberinfo memberinfo : prinList) {
			System.out.println(memberinfo.getNickname());
		}*/
		//返回符合条件的好友列表
		return query.list();
	}

	@Override
	public void saveMessage(Messagerecord message) throws DataAccessException {
		Session session = HibernateSessionFactory.getSession();
		session.saveOrUpdate(message);
	}

	@Override
	public List<Messagerecord> listSendMessage(String senderName)
			throws DataAccessException {
		Session session = HibernateSessionFactory.getSession();
		//hql语句：首先查找年龄范围
		String hql = "from Messagerecord where sender =?";
		Query query = session.createQuery(hql);
		query.setString(0, senderName);
	      List<Messagerecord> messagerecords = query.list();
	      if (messagerecords.size()>0) {
	        return messagerecords;
	      }
	      return null;
	}

	@Override
	public List<Messagerecord> listRecieveMessage(String recieveName)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Messagerecord findMessage(Long id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRecieveMessage(Long id) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSendMessage(Long id) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Memberinfo findNotFriend(String selfname)
			throws MessengerServiceException {
		Session session = HibernateSessionFactory.getSession();
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
		}
	}

}
