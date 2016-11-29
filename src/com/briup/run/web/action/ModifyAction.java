package com.briup.run.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.exception.MemberServiceException;
import com.briup.run.common.util.BeanFactory;
import com.briup.run.service.impl.MemberServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class ModifyAction extends ActionSupport implements ServletRequestAware{
  //MemberInfo表
  private Memberinfo memberinfo;
  //旧密码
  private String oldPasswd;
  //新密码
  private String newPasswd;
  //邮箱
  private String email;
  //密码提示问题
  private String passwdQuestion;
  //密码提示问题答案
  private String passwdAnswer;
  //性别
  private String gender;
  //所在省份城市
  private String provinceCity;
  //联系电话
  private String phone;
  //详细地址
  private String address;
  //memServicr方法
  private MemberServiceImpl memberServiceImpl = (MemberServiceImpl) BeanFactory.getBean(BeanFactory.MEMBERSERVICE);
  //request
  private HttpServletRequest request;
  
  
  //错误信息
  private String msg;
  
  public String modify() {
    //获取login的session对象
    memberinfo = (Memberinfo) request.getSession().getAttribute("member");
    System.out.println(memberinfo);
    //判断填入的旧密码是否为空或者与原旧密码不相等
    if ("".equals(getOldPasswd()) || !memberinfo.getPassword().equals(oldPasswd)) {
      msg="旧密码错误！";
      return ERROR;
    }
    //设置新的属性
    //新密码
    memberinfo.setPassword(getNewPasswd());
    //邮箱
    memberinfo.setEmail(email);
    //密码提示问题
    memberinfo.setPasswordquestion(passwdQuestion);
    //密码提示问题答案
    memberinfo.setPasswordanswer(passwdAnswer);
    //性别
    memberinfo.setGender(gender);
    //省份
    memberinfo.setProvincecity(provinceCity);
    //联系电话
    memberinfo.setPhone(phone);
    //详细地址
    memberinfo.setAddress(address);
    
    //保存
    try {
      System.out.println(memberinfo);
      memberServiceImpl.saveOrUpdate(memberinfo);
    } catch (MemberServiceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return SUCCESS;
  }
  
  public Memberinfo getMemberinfo() {
    return memberinfo;
  }
  public void setMemberinfo(Memberinfo memberinfo) {
    this.memberinfo = memberinfo;
  }

  public String getOldPasswd() {
    return oldPasswd;
  }
  
  public void setOldPasswd(String oldPasswd) {
    this.oldPasswd = oldPasswd;
  }
 
  public String getNewPasswd() {
    return newPasswd;
  }

  public void setNewPasswd(String newPasswd) {
    this.newPasswd = newPasswd;
  }
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPasswdQuestion() {
    return passwdQuestion;
  }

  public void setPasswdQuestion(String passwdQuestion) {
    this.passwdQuestion = passwdQuestion;
  }

  public String getPasswdAnswer() {
    return passwdAnswer;
  }

  public void setPasswdAnswer(String passwdAnswer) {
    this.passwdAnswer = passwdAnswer;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
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
  
  @Override
  public void setServletRequest(HttpServletRequest arg0) {
    // TODO Auto-generated method stub
    this.request = arg0;
  }

}

