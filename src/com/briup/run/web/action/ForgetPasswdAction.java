package com.briup.run.web.action;

import com.briup.run.common.util.BeanFactory;
import com.briup.run.service.impl.MemberServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class ForgetPasswdAction extends ActionSupport {
  
  private String userName;
  private String passwdQuestion;
  private String passwdAnswer;
  
  private String msg;
  MemberServiceImpl memberService = (MemberServiceImpl) BeanFactory.getBean(BeanFactory.MEMBERSERVICE); 
  public String forgetPasswd() throws Exception {
    try {
      msg="修改成功！新密码："+memberService.getBackPasswd(userName, passwdQuestion, passwdAnswer);
      return SUCCESS;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      msg="修改失败："+e.getMessage();
      return ERROR;
    }
  }
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
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
  public String getMsg() {
    return msg;
  }
  public void setMsg(String msg) {
    this.msg = msg;
  }
}

