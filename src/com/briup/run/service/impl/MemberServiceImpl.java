package com.briup.run.service.impl;

import java.util.Date;
import java.util.Iterator;
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
import com.briup.run.common.transaction.HibernateTransaction;
import com.briup.run.common.util.BeanFactory;
import com.briup.run.common.util.DateUtils;
import com.briup.run.common.util.HibernateSessionFactory;
import com.briup.run.common.util.RandomChar;
import com.briup.run.dao.impl.MemberDaoImpl;
import com.briup.run.service.IMemberService;

public class MemberServiceImpl implements IMemberService {

  private MemberDaoImpl memberDaoImpl = (MemberDaoImpl) BeanFactory
      .getBean(BeanFactory.MEMBERDAO);
  private HibernateTransaction transaction = new HibernateTransaction();

  @Override
  public void registerMemberinfo(Memberinfo memberinfo)
      throws MemberServiceException {
    try {
      // 查找用户是否存在
      Memberinfo findmemberinfo = memberDaoImpl.findMemberinfoByName(memberinfo
          .getNickname());
      if (findmemberinfo != null) {
        throw new MemberServiceException("用户名重复");
      }
      // 在事务层开启事务：原子操作
      transaction.beginTransaction();

      // 调用查找对应的加分项目方法。并用对象接受
      Pointaction pointaction = memberDaoImpl
          .findPointactionByPointAction("REGISTER");

      // 设置得到的积分
      memberinfo.setPoint(pointaction.getPoint());

      // 查找对应的等级，给用户设置相应的等级
      Graderecord graderecord = memberDaoImpl.findMemberinfoLevel(pointaction
          .getPoint());
      memberinfo.setGraderecord(graderecord);

      // 记录当前积分动作
      Pointrecord pointrecord = new Pointrecord();
      pointrecord.setNickname(memberinfo.getNickname());
      pointrecord.setPointaction(pointaction);
      pointrecord.setReceivedate(new Date());
      memberDaoImpl.saveOrUpdatePointrecord(pointrecord);

      // 查找是否有推荐人
      String recommender = memberinfo.getRecommender();
      if (!"".equals(recommender) && recommender != null) {

        // 7.查找推荐人
        Memberinfo recmemberinfo = memberDaoImpl
            .findMemberinfoByName(recommender);
        // 推荐人存在
        if (recmemberinfo != null) {
          // 调用查找对应的加分项目方法。并用对象接受
          Pointaction recpointaction = memberDaoImpl
              .findPointactionByPointAction("RECOMMEND");
          // 设置推荐人积分:新加的积分加上原来的积分
          recmemberinfo.setPoint(recpointaction.getPoint()
              + recmemberinfo.getPoint());
          // 查找对应的等级，给推荐用户设置相应的等级
          Graderecord recgraderecord = memberDaoImpl
              .findMemberinfoLevel(recmemberinfo.getPoint());
          recmemberinfo.setGraderecord(recgraderecord);
          // 更新推荐人信息
          memberDaoImpl.saveOrUpdateMemberinfo(recmemberinfo);

          // 记录推荐人的动作
          Pointrecord recpointrecord = new Pointrecord();
          recpointrecord.setNickname(recmemberinfo.getNickname());
          recpointrecord.setPointaction(recpointaction);
          recpointrecord.setReceivedate(new Date());
          memberDaoImpl.saveOrUpdatePointrecord(recpointrecord);
        }
      }
      // 设置注册账户其他信息
      // 设置账户的状态
      memberinfo.setStatus(0L);
      // 设置在线状态
      memberinfo.setIsonline(0L);
      // 设置注册时间
      memberinfo.setRegisterdate(new Date());
      // 14.保存
      memberDaoImpl.saveOrUpdateMemberinfo(memberinfo);
      transaction.commit();
    } catch (DataAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      // 抛出异常则回滚。
      transaction.rollback();
    }
    /*
     * 少关事务 if (session!=null) { session.close(); }
     */
  }

  @Override
  public Memberinfo findMemberinfoByName(String nickname)
      throws MemberServiceException {
    // TODO Auto-generated method stub
    try {
      return memberDaoImpl.findMemberinfoByName(nickname);
    } catch (DataAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Memberinfo login(String username, String passwd)
      throws MemberServiceException {
    // TODO Auto-generated method stub
    //开启事务
    transaction.beginTransaction();
    try {
      // 1.查找用户：用戶不存在，用戶存在
      Memberinfo memberinfo = memberDaoImpl.findMemberinfoByName(username);
      // 用戶不存在，拋出異常
      if (memberinfo == null) {
        throw new MemberServiceException("用户名不存在");
      }
      // 用戶存在
      // 2.判断该用户是否被注销
      if (memberinfo.getStatus()==1) {
        throw new MemberServiceException("该用户被注销");
      }
        //该用户正常
      //3.判断密码  密码错误
      if (!memberinfo.getPassword().equals(passwd)) {
        throw new MemberServiceException("用户名或者密码错误！");
      }
      //4.判断上一次登录时间是否与当前日期相同
      if (memberinfo.getLatestdate()==null || !DateUtils.isSameDate(memberinfo.getLatestdate(), new Date())) {
        //5.查找对应的加分项目:"LOGIN"
        Pointaction pointaction = memberDaoImpl.findPointactionByPointAction("LOGIN");
        //6.之前的积分加上login积分
        memberinfo.setPoint(memberinfo.getPoint()+pointaction.getPoint());
        //7.查找积分对应的等级
        Graderecord graderecord = memberDaoImpl.findMemberinfoLevel(memberinfo.getPoint());
        //8.设置用户当前对应等级
        memberinfo.setGraderecord(graderecord); 
        //设置在线状态:在线
        memberinfo.setIsonline(1L);
        //9.记录积分动作
        Pointrecord pointrecord = new Pointrecord();
        pointrecord.setNickname(memberinfo.getNickname());
        pointrecord.setPointaction(pointaction);
        pointrecord.setReceivedate(new Date());
        memberDaoImpl.saveOrUpdatePointrecord(pointrecord);
      }
      //设置用户登录时间
        memberinfo.setLatestdate(new Date());
      //10.更新用户信息
      memberDaoImpl.saveOrUpdateMemberinfo(memberinfo);
      //11.提交事务
      transaction.commit();
      //12.返回memberinfo
      return memberinfo;
    } catch (DataAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      System.out.println("未知错误");
      return null;
    }
  }

  @Override
  public void logout(String nickname) throws MemberServiceException {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Memberinfo> findMemberinfoByNum(int number)
      throws MemberServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int findMemberinfoOnline() throws MemberServiceException {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Graderecord findMemberinfoLevel(Long point)
      throws MemberServiceException {
    // TODO Auto-generated method stub
    // from 这个类。查找这个条件（） from XX where minpoint<= ? and maxpoint;
    Session session = HibernateSessionFactory.getSession();
    Query query = session
        .createQuery("from Graderecord where minpoint<=? and maxpoint>=?");
    query.setLong(0, point);
    query.setLong(1, point);

    Graderecord graderecord = (Graderecord) query.uniqueResult();
    return graderecord;
  }

  @Override
  public Memberinfo saveOrUpDate(Memberinfo memberinfo, String oldPasswd)
      throws MemberServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  //找回密码
  @Override
  public String getBackPasswd(String nickname, String pwdQuestion,
      String pwdAnswer) throws MemberServiceException {
    //开启事务
    transaction.beginTransaction();
    try {
      //获取用户
      Memberinfo memberinfo = memberDaoImpl.findMemberinfoByName(nickname);
      //判断用户是否存在
      if (memberinfo==null) {
        throw new MemberServiceException("用户名不存在");
      }
      //判断验证问题是否相同
      if (!memberinfo.getPasswordanswer().equals(pwdQuestion) || !memberinfo.getPasswordquestion().equals(pwdQuestion)) {
        throw new MemberServiceException("密码提示问题或者答案错误！");
      }
      //生成随机密码
      RandomChar randomChar = new RandomChar();
      //获取新的密码
      String newPasswd = randomChar.getChars(4,8);
      //将新密码保存
      memberinfo.setPassword(newPasswd);
      memberDaoImpl.saveOrUpdateMemberinfo(memberinfo);
      //提交事务
      transaction.commit();
      //返回新的密码
      return newPasswd;
    } catch (DataAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void saveOrUpdate(Memberinfo memberinfo) throws MemberServiceException {
    // 在事务层开启事务：原子操作
    transaction.beginTransaction();
    try {
      memberDaoImpl.saveOrUpdateMemberinfo(memberinfo);
    } catch (DataAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //提交事务
    transaction.commit();
  }

  @Override
  public void saveOrUpdate(String selfname, String friendname)
      throws MemberServiceException {
	  // 在事务层开启事务：原子操作
	  transaction.beginTransaction();
	  try {
		//查找是否已经是好友
		  System.out.println("查找是否已经是好友");
		Friendrecord friendrecord = memberDaoImpl.findfriend(selfname, friendname);
		//如果是自己
		if (selfname.equals(friendname)) {
			throw new MemberServiceException("不能添加自己为好友");
		}
		//如果已经存在
		if (friendrecord!=null) {
			throw new MemberServiceException("该会员已经是你的好友");
		}
		//查找是否是黑名单
		System.out.println("查找是否是黑名单");
		Blackrecord blackrecord = memberDaoImpl.findBlack(selfname, friendname);
		if (blackrecord!=null) {
			throw new MemberServiceException("该会员再你的黑名单，请去黑名单操作");
		}
		//new 一个friendRecord用来保存
		Friendrecord friendrecord2 = new Friendrecord();
		System.out.println("设置Friendrecord的friendname");
		friendrecord2.setFriendname(friendname);
		System.out.println("设置Friendrecord的selfname");
		friendrecord2.setSelfname(selfname);
		//保存
		memberDaoImpl.saveOrUpdateFriend(friendrecord2);
		System.out.println("保存Friendrecord");
		//提交事务
		transaction.commit();
		System.out.println("提交事务");
		
	} catch (DataAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }

  @Override
  public List<Memberinfo> listFriend(String selfname)
      throws MemberServiceException {
	  try {
		List<Memberinfo> listFriend = memberDaoImpl.listFriend(selfname);
		for (Memberinfo memberinfo : listFriend) {
			System.out.println("输出好友名字："+memberinfo.getNickname());
		}
		return listFriend;
	} catch (DataAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
  }

  @Override
  public void moveToBlack(String selfname, String blackname)
      throws MemberServiceException {
	// 在事务层开启事务：原子操作
		transaction.beginTransaction();
		// new 一个friendRecord用来保存
		Blackrecord blackrecord = new Blackrecord();
		System.out.println("设置Blackrecord的blackname");
		blackrecord.setBlackname(blackname);
		System.out.println("设置Blackrecord的selfname");
		blackrecord.setSelfname(selfname);
		// 保存
		try {
			memberDaoImpl.saveOrUpdateFriend(blackrecord);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("保存Blackrecord");
		// 提交事务
		transaction.commit();
		System.out.println("提交事务");
  }

  @Override
  public List<Memberinfo> listBlack(String selfname)
      throws MemberServiceException {
	  try {
		  List<Memberinfo> listBlack = memberDaoImpl.listBlack(selfname);
		  return listBlack;
	} catch (DataAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
  }

  @Override
  public Friendrecord findFriend(String friend) throws MemberServiceException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Boolean isMemberspace(Long id) throws MemberServiceException {
    // TODO Auto-generated method stub
    return memberDaoImpl.isMemberspace(id);
  }

  @Override
  public void moveToFriend(String selfName, String name_searching)
      throws MemberServiceException {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleleBlack(String selfName, String black)
      throws MemberServiceException {
    // TODO Auto-generated method stub
	//在事务层开启事务：原子操作
	try {
		transaction.beginTransaction();
		//获取需要删除的黑名单记录
		  Blackrecord blackrecord = memberDaoImpl.findBlack(selfName, black);
		  memberDaoImpl.deleleBlack(blackrecord);
		  transaction.commit();
		  System.out.println("关闭事务");
	} catch (DataAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  @Override
  public void deleleFriend(String selfName, String friend)
      throws MemberServiceException {
	  try {
		  //在事务层开启事务：原子操作
		  transaction.beginTransaction();
		  //获取需要删除的好友记录
		  Friendrecord friendrecord = memberDaoImpl.findfriend(selfName, friend);
		  System.out.println("获取需要删除的好友记录");
		  memberDaoImpl.deleleFriend(friendrecord);
		  System.out.println("调用memberDaoImpl.deleleFriend删除好友记录");
		  
		  transaction.commit();
		  System.out.println("关闭事务");
	} catch (DataAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  @Override
  public void delSpace(Long id) throws MemberServiceException {
    // TODO Auto-generated method stub

  }
  //查找积分动作
  @Override
  public Pointaction findPointactionByPointAction(String actionName)
      throws MemberServiceException {
    // TODO Auto-generated method stub
    try {
      return memberDaoImpl.findPointactionByPointAction(actionName);
    } catch (DataAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void saveOrUpdatePointrecord(Pointrecord pointrecord) throws MemberServiceException {
    // TODO Auto-generated method stub
    try {
      memberDaoImpl.saveOrUpdatePointrecord(pointrecord);
    } catch (DataAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  //保存或者更新用户空间
  @Override
  public void saveOrUpdateSpace(Memberspace memberspace) throws MemberServiceException {
    //开启事务
    transaction.beginTransaction();
    memberDaoImpl.saveOrUpdateSpace(memberspace);
    //提交事务
    transaction.commit();
  }

  @Override
  public Memberspace findSpace(Long id) throws DataAccessException {
    // TODO Auto-generated method stub
    //判断是否有个人空间
    
    return null;
  }

}
