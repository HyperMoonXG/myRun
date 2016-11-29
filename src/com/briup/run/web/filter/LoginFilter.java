package com.briup.run.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.run.common.bean.Memberinfo;

public class LoginFilter implements Filter{

  @Override
  public void destroy() {
    // TODO Auto-generated method stub
    System.out.println("destroy Filter");
  }

  @Override
  public void doFilter(ServletRequest arg0, ServletResponse arg1,
      FilterChain arg2) throws IOException, ServletException {
    //将父接口ServletRequest转换为HttpServletRequest
    HttpServletRequest request = (HttpServletRequest) arg0;
    
    //将父接口ServletResponse转换为HttpServletResponse
    HttpServletResponse response = (HttpServletResponse) arg1;
    
    //再session中获取memberinfo
    Memberinfo memberinfo = (Memberinfo) request.getSession().getAttribute("member");
    
    //判断memberinfo是否为空
    if (memberinfo == null) {
    	System.out.println("memberinfo信息："+memberinfo);
      //为空则内部跳转login
      request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    else {
      arg2.doFilter(request, response);
    }
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
    // TODO Auto-generated method stub
    System.out.println("init Filter");
  }
  
}
