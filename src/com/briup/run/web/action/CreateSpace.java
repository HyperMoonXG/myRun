package com.briup.run.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.briup.run.common.bean.Graderecord;
import com.briup.run.common.bean.Memberinfo;
import com.briup.run.common.bean.Memberspace;
import com.briup.run.common.bean.Pointaction;
import com.briup.run.common.bean.Pointrecord;
import com.briup.run.common.util.BeanFactory;
import com.briup.run.service.impl.MemberServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class CreateSpace extends ActionSupport implements ServletRequestAware{
  //跑步的主张
  private String opinion;
  //跑步时段
  private String runtime;
  //跑步习惯：单独 结伴
  private String runhabit;
  //体育明星
  private String runstar;
  //使用的手机
  private String cellphone;
  //跑步的地点
  private String runplace;
  //头像文件：文件，文件名
  private File icon;
  private String iconFileName;
//  private String iconPath;
  //用户信息memberinfo
  private Memberinfo memberinfo;
  //memberServiceImpl方法
  private MemberServiceImpl memberServiceImpl = (MemberServiceImpl) BeanFactory
      .getBean(BeanFactory.MEMBERSERVICE);
  private HttpServletRequest request;

  public String createSpace() throws Exception {
    // 获取当前登录对象
    HttpSession session = request.getSession();
    memberinfo = (Memberinfo) session.getAttribute("member");
    if (icon!=null) {
      getFilePath(icon, getIconFileName());
    }
    //判断是否有用户空间。如果不存在。new一个
    if (memberinfo.getMemberSpace()==null) {
      //new 一个Memberspace
      Memberspace memberspace = new Memberspace();
      //设置 memberinfo的Memberspace
      memberinfo.setMemberSpace(memberspace);
      //查找积分动作
      Pointaction pointaction = memberServiceImpl.findPointactionByPointAction("CREATEPERSONALSPACE");
      //更新memberinfo的积分
      memberinfo.setPoint(memberinfo.getPoint()+pointaction.getPoint());
      //记录当前积分动作
      Pointrecord pointrecord = new Pointrecord();
      pointrecord.setNickname(memberinfo.getNickname());
      pointrecord.setPointaction(pointaction);
      pointrecord.setReceivedate(new Date());
      memberServiceImpl.saveOrUpdatePointrecord(pointrecord);
      //查找对应的等级
      Graderecord graderecord = memberServiceImpl.findMemberinfoLevel(memberinfo.getPoint());
      //更新memberinfo的等级
      memberinfo.setGraderecord(graderecord);
      //设置Memberspace的memberinfo(更新memberspace的memberinfo)
      memberinfo.getMemberSpace().setMemberinfo(memberinfo);
    }
    
    //如果存在就直接存入各种属性
    //存入opinion
    memberinfo.getMemberSpace().setOpinion(opinion);
    //存入runtime
    memberinfo.getMemberSpace().setRuntime(runtime);
    //存入runhabit
    memberinfo.getMemberSpace().setRunhabit(runhabit);
    //存入runstar
    memberinfo.getMemberSpace().setRunstar(runstar);
    //存入cellphone
    memberinfo.getMemberSpace().setCellphone(cellphone);
    //存入runplace
    memberinfo.getMemberSpace().setRunplace(runplace);
    //存入icon
    memberinfo.getMemberSpace().setIcon(iconFileName);
    //更新session的memberinfo的信息
    session.setAttribute("member", memberinfo);
    //保存memberinfo中的memberspace
    memberinfo.setMemberSpace(memberinfo.getMemberSpace());
    //保存数据库信息
    memberServiceImpl.saveOrUpdate(memberinfo);
    return SUCCESS;
    
  }

  private void getFilePath(File file, String fileName) {
    // 获取上传的路径
    String path = ServletActionContext.getServletContext().getRealPath(
        "/upload/memberspace");
    // 打印：测试
    System.out.println("file:" + file);
    System.out.println("fileFileName:" + fileName);

    // 输出上传的路径
    System.out.println("path" + path);
    if (file != null) {
      File savedir = new File(path);
      // 如果不存在,则创建
      if (!savedir.exists()) {
        savedir.mkdir();
        System.out.println("创建文件夹");
      }
      // 路径拼接：（目标的路径）
      File dest = new File(savedir, fileName);
      // 源文件，目标文件 
      try {
        FileUtils.copyFile(file, dest);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      System.out.println("上传文件成功！文件地址：" + dest.toString());
    }
  }

  
  public String getOpinion() {
    return opinion;
  }

  public void setOpinion(String opinion) {
    this.opinion = opinion;
  }

  public String getRuntime() {
    return runtime;
  }

  public void setRuntime(String runtime) {
    this.runtime = runtime;
  }

  public String getRunhabit() {
    return runhabit;
  }

  public void setRunhabit(String runhabit) {
    this.runhabit = runhabit;
  }

  public String getRunstar() {
    return runstar;
  }

  public void setRunstar(String runstar) {
    this.runstar = runstar;
  }

  public String getCellphone() {
    return cellphone;
  }

  public void setCellphone(String cellphone) {
    this.cellphone = cellphone;
  }

  public String getRunplace() {
    return runplace;
  }

  public void setRunplace(String runplace) {
    this.runplace = runplace;
  }

  public File getIcon() {
    return icon;
  }

  public void setIcon(File icon) {
    this.icon = icon;
  }


  public HttpServletRequest getRequest() {
    return request;
  }

  public void setRequest(HttpServletRequest request) {
    this.request = request;
  }

  public Memberinfo getMemberinfo() {
    return memberinfo;
  }

  public void setMemberinfo(Memberinfo memberinfo) {
    this.memberinfo = memberinfo;
  }

  public MemberServiceImpl getMemberServiceImpl() {
    return memberServiceImpl;
  }

  public void setMemberServiceImpl(MemberServiceImpl memberServiceImpl) {
    this.memberServiceImpl = memberServiceImpl;
  }

  @Override
  public void setServletRequest(HttpServletRequest arg0) {
    // TODO Auto-generated method stub
    this.request = arg0;
  }

  @Override
  public String toString() {
    return "CreateSpace [opinion=" + opinion + ", runtime=" + runtime
        + ", runhabit=" + runhabit + ", runstar=" + runstar + ", cellphone="
        + cellphone + ", runplace=" + runplace + ", icon=" + icon
        + ", iconName=" + iconFileName + "]";
  }

  public String getIconFileName() {
    return iconFileName;
  }

  public void setIconFileName(String iconFileName) {
    this.iconFileName = iconFileName;
  }

  
}
