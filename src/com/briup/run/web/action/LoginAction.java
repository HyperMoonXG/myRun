package com.briup.run.web.action;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.util.BeanFactory;
import com.briup.run.service.impl.MemberServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
/*  //用户名
  private String name;
  //密码
  private String password;*/
  private Memberinfo memberinfo;
  //获取验证码
  private String authCode;
  //HS
  private HttpServletRequest request;
  private HttpServletResponse resp;
  //错误信息
  private String msg;
  //自动登录:0代表自动登录
  private String autoLogin;
  //IMS
  MemberServiceImpl memberService = (MemberServiceImpl) BeanFactory.getBean(BeanFactory.MEMBERSERVICE); 
  public String login() throws Exception {
   
    //获取验证码
    HttpSession session = request.getSession();
    String code = (String) session.getAttribute("authCode");
    //判断验证码:null 空填写错误
    if (code==null || "".equals(code) || !code.equals(authCode)) {
      msg="验证码错误";
      return ERROR;
    }
    try {
      System.out.println(memberinfo);
      Memberinfo member = memberService.login(memberinfo.getNickname(), memberinfo.getPassword());
      //前台可以获取信息，输出
      session.setAttribute("member", member);
      System.out.println("登录的session======"+session.getAttribute("member"));
      System.out.println("登录的member======"+member.getNickname());
      System.out.println("登录成功！");
      
      //查找个人空间：未知用途？？
      //memberService.findSpace(memberinfo.getId());
      
      //判断是否自动登录
      if ("0".equals(autoLogin)) {
        //需要编码：如果用户名是中文
        String name = URLEncoder.encode(memberinfo.getNickname(), "utf-8");
        //创建cookie对象：用户名，密码
        Cookie cookieNickname = new Cookie("nickname",name);
        Cookie cookiePassword = new Cookie("password",memberinfo.getPassword());
        //设置cookie生命周期：2周
        cookieNickname.setMaxAge(1209600);
        cookiePassword.setMaxAge(1209600);
        //将cookie写入浏览器
        resp.addCookie(cookieNickname);
        resp.addCookie(cookiePassword);
      }
      return SUCCESS;
    } catch (Exception e) {
      // TODO Auto-generated catch block
     // e.printStackTrace();
      msg="登录失败"+e.getMessage();
      return ERROR;
    }
  }
  

  public String getAuthCode() {
    return authCode;
  }


  public void setAuthCode(String authCode) {
    this.authCode = authCode;
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


  @Override
  public void setServletResponse(HttpServletResponse arg0) {
    // TODO Auto-generated method stub
    this.resp = arg0;
  }


  public String getAutoLogin() {
    return autoLogin;
  }


  public void setAutoLogin(String autoLogin) {
    this.autoLogin = autoLogin;
  }


  public Memberinfo getMemberinfo() {
    return memberinfo;
  }


  public void setMemberinfo(Memberinfo memberinfo) {
    this.memberinfo = memberinfo;
  }
  
}

