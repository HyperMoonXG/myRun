package com.briup.run.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodeFilter implements Filter{

  @Override
  public void destroy() {
    // TODO Auto-generated method stub
    System.out.println("摧毁Filter");
  }

  @Override
  public void doFilter(ServletRequest arg0, ServletResponse arg1,
      FilterChain arg2) throws IOException, ServletException {
    // TODO Auto-generated method stub
      arg0.setCharacterEncoding("utf-8");
      arg1.setCharacterEncoding("utf-8");
      arg2.doFilter(arg0, arg1);
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
    // TODO Auto-generated method stub
    System.out.println("初始化Filter");
  }
  
}
