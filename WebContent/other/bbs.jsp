<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset="utf-8" />
<meta name="keywords" content="杰普软件(www.briup.com)" />
<meta name="description" content="杰普软件(www.briup.com)" />
<title>杰普——跑步社区</title>
<link rel="stylesheet" type="text/css" id="css" href="style/main.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style1.css" />
<script src="js/main.js" type="text/javascript"></script>
</head>
<body>
<div id="btm">
<div id="main">

  <div id="header">
    <div id="top"></div>
    <div id="logo"><h1>跑步社区</h1></div>
	<div id="logout">
	<a href="login.jsp">注  销</a> | 收  藏
	 </div>
    <div id="mainnav">
      <ul>
      <li><a href="member/activity.jsp">首页</a></li>
      <li><a href="other/musicrun.jsp">音乐跑不停</a></li>
      <li><a href="other/equip.jsp">跑步装备库</a></li>
      <li><a href="other/guide.jsp">专业跑步指南</a></li>
      <li class="current"><a href="other/bbs.jsp">跑步论坛</a></li>

      </ul>
      <span></span>    </div>
   </div>
  
  <div id="content">
     
     <div id="sc">
        <p class="weight">跑步论坛</p>
        <p>
　        　
        </p>   
        <div id="coms">
          <table id="listofcoms" cellpadding="0" cellspacing="0" height="200px">
            <tr>
            <td colspan="3" id="toptitle"></td>
            <tr>
			<tr>
            <td></td><td></td><td class="borderright"></td>
            </tr>
            
          </table>
        </div>   
     </div>
          
  </div>
  
  <div id="footer">
    <div id="copyright">
       <div id="copy">
       <p>CopyRight&copy;2009</p>
       <p>跑步社区(www.irun.com) </p>
        </div>
    </div>
    <div id="bgbottom"></div>
  </div>
  
</div>
</div>
</body>
</html>

