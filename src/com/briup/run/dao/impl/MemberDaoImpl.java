package com.briup.run.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.briup.run.common.bean.Blackrecord;
import com.briup.run.common.bean.Friendrecord;
import com.briup.run.common.bean.Graderecord;
import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.bean.Memberspace;
import com.briup.run.common.bean.Pointaction;
import com.briup.run.common.bean.Pointrecord;
import com.briup.run.common.exception.DataAccessException;
import com.briup.run.common.exception.MemberServiceException;
import com.briup.run.common.util.HibernateSessionFactory;
import com.briup.run.dao.IMemberDao;

public class MemberDaoImpl implements IMemberDao {

	@Override
	public Memberinfo findMemberinfoByName(String name)
			throws DataAccessException {
		// TODO Auto-generated method stub
	  Session session = HibernateSessionFactory.getSession();
	  
	  Query query= session.createQuery("from Memberinfo memberinfo where memberinfo.nickname=?");
	  query.setString(0, name);
	  List<Memberinfo> listMemberinfo = query.list();
	  if (listMemberinfo.size()>0) {
        return listMemberinfo.get(0);
      }
	      return null;
	}

	@Override
	public void saveOrUpdateMemberinfo(Memberinfo memberinfo)
			throws DataAccessException {
		// TODO Auto-generated method stub
	  Session session = HibernateSessionFactory.getSession();
	  System.out.println("now try to save memberinfo into table:memberinfo");
	  session.saveOrUpdate(memberinfo);
	}

	@Override
	public List<Memberinfo> findMemberinfoByNum(int number)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer findMemberinfoOnline() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graderecord findMemberinfoLevel(Long point)
			throws DataAccessException {
		// TODO Auto-generated method stub
	  Session session = HibernateSessionFactory.getSession();
	  Query query = session.createQuery("from Graderecord where minpoint<=? and maxpoint>=?");
	  query.setLong(0, point);
	  query.setLong(1, point);
	  return (Graderecord) query.uniqueResult();
	}

	@Override
	public Pointaction findPointactionByPointAction(String pointAction)
			throws DataAccessException {
		// TODO Auto-generated method stub
	  Session session = HibernateSessionFactory.getSession();
//      查找对应的分数行
	  
      Query query= session.createQuery("from Pointaction where actionname=?");
      query.setString(0, pointAction);
      List<Pointaction> listPointaction = query.list();
      if (listPointaction.size()>0) {
        return listPointaction.get(0);
      }
		return null;
	}

	@Override
	public void saveOrUpdatePointrecord(Pointrecord pointrecord)//
			throws DataAccessException {
		// TODO Auto-generated method stub
	   Session session = (Session) HibernateSessionFactory.getSession();
	      System.out.println("now try to save memberinfo into table:pointrecord");
	      session.save(pointrecord);
	}

	@Override
	public void saveOrUpdateFriend(Friendrecord friend)
			throws DataAccessException {
		Session session = HibernateSessionFactory.getSession();
		session.saveOrUpdate(friend);
	}

	@Override
	public void saveOrUpdateFriend(Blackrecord friend)
			throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		session.saveOrUpdate(friend);
	}

	@Override
	public List<Memberinfo> listFriend(String selfname)
			throws DataAccessException {
		System.out.println("输出传入的selfname："+selfname);
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Memberinfo where nickname in (select friendname from Friendrecord where selfname =:selfname)");
		query.setString("selfname",selfname);
		return query.list();
	}

	@Override
	public List<Memberinfo> listBlack(String selfname)
			throws DataAccessException {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Memberinfo where nickname in (select blackname from Blackrecord where selfname =:selfname)");
		query.setString("selfname",selfname);
		return query.list();
	}

	@Override
	public void deleleBlack(Blackrecord black) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		session.delete(black);
	}

	@Override
	public void deleleFriend(Friendrecord friend) throws DataAccessException {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		session.delete(friend);
	}

	@Override
	public Friendrecord findfriend(String selfName, String friendName)
			throws DataAccessException {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Friendrecord where selfname =:selfname and friendname =:friendname");
		query.setString("selfname", selfName);
		query.setString("friendname", friendName);
		List<Friendrecord> list = query.list();
		if (list.size()>0) {
			System.out.println("输出好友："+list.get(0).getFriendname());
			return list.get(0);
		}
		return null;
	}
	

	@Override
	public Blackrecord findBlack(String selfName, String blackName)
			throws DataAccessException {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Blackrecord where selfname =:selfname and blackname =:blackname");
		query.setString("selfname", selfName);
		query.setString("blackname", blackName);
		List<Blackrecord> list = query.list();
		if (list.size()>0) {
			System.out.println("输出黑名单："+list.get(0).getBlackname());
			return list.get(0);
		}
		return null;
	}

	@Override
	public Memberspace findSpace(Long id) throws DataAccessException {
	  
		return null;
	}

	@Override
	public void delSpace(Memberspace space) throws DataAccessException {
		// TODO Auto-generated method stub

	}

  @Override
  public void saveOrUpdateSpace(Memberspace memberspace)
      throws MemberServiceException {
    Session session = (Session) HibernateSessionFactory.getSession();
    System.out.println("now try to save memberspace into table:memberspace");
    session.saveOrUpdate(memberspace);
  }

  //判断是否有个人空间
  @Override
  public Boolean isMemberspace(Long id) throws MemberServiceException {
    Session session = HibernateSessionFactory.getSession();
    Query query = session.createQuery("from Memberspace where memberinfo=?");
    query.setLong(0, id);
    List<Memberspace> listMemberinfo = query.list();
    if (listMemberinfo.size()>0) {
      return true;
    }
    else{
      return false;
    }
  }

}
