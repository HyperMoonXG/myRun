<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.briup.run.common.bean.Memberinfo" %>
<%@page import="com.briup.run.common.util.Util" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset="utf-8" />
<meta name="keywords" content="杰普软件(www.briup.com)" />
<meta name="description" content="杰普软件(www.briup.com)" />
<title>杰普——跑步社区</title>
<link rel="stylesheet" type="text/css" id="css" href="style/main.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style1.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style.css" />

<style type="text/css">
<!--
table{border-spacing:1px; border:1px solid #A2C0DA;}
td, th{padding:2px 5px;border-collapse:collapse;text-align:left; font-weight:normal;}
thead tr th{background:#B0D1FC;border:1px solid white;}
thead tr th.line1{background:#D3E5FD;}
thead tr th.line4{background:#C6C6C6;}
tbody tr td{height:35px;background:#CBE2FB;border:1px solid white; vertical-align:middle;}
tbody tr td.line4{background:#D5D6D8;}
tbody tr th{height:35px;background: #DFEDFF;border:1px solid white; vertical-align:middle;}
tfoot tr td{height:35px;background:#F5F5F5;border:1px solid white; vertical-align:middle; text-align:right}
-->
</style>

<script src="js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="js/common.js" ></script>

</head>
<body>
<div id="btm">
<div id="main">

  <div id="header">
    <div id="top"></div>
    <div id="logo">
      <h1>跑步社区</h1></div>
	  <div id="logout">
	<a href="login.jsp">注  销</a> | 收  藏
	 </div>
    <div id="mainnav">
      <ul>
      <li><a href="member/activity.jsp">首页</a></li>
      <li><a href="other/musicrun.jsp">音乐跑不停</a></li>
      <li><a href="other/equip.jsp">跑步装备库</a></li>
      <li><a href="other/guide.jsp">专业跑步指南</a></li>
      <li><a href="other/bbs.jsp">跑步论坛</a></li>
	
      </ul>
      <span></span>
    </div>
   </div>
   
  <div id="tabs1">
  <ul>
    <li><a href="messenger/listMembers2.action" title="好友速配"><span><b>好友速配</b></span></a></li>
    <li><a href="messenger/listFriends.action" title="好友名单"><span>好友名单</span></a></li>
    <li><a href="messenger/blackList.action" title="黑名单"><span>黑名单</span></a></li>
  </ul>
</div>
<br /><br />

  <div id="content" align="center"> 
     <div id="center">
	 <br /><br />
		<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td colspan="2" height="40">
						<h4>-&gt;好友速配</h4>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td width="100%">
						<form action="messenger/matchFriend.action" method="post" name="matchFriendForm">
						<table width="100%" cellspacing="1" cellpadding="3" align="center">
							<tr>
								<td colspan="4">
									<b>找到您跑步世界的另一半，您可以 <a href="messenger/randMatchFriend.action"><FONT color=#ff0000>马上速配</FONT></a> 一位跑友</b>
								</td>
							</tr>
							<tr>
								<td colspan="4"><b>更可以按要求检索</b></td>
							</tr>
							<tr>
         						 <td align="center">年龄范围：
         						 	<select name="age">
                  						<option value="unlimited">不限</option>
                  						<option value="1">10-19岁</option>
                  						<option value="2">20-29岁</option>
                  						<option value="3">30-39岁</option>
                    				</select>
                    			</td>
           						<td align="center">性别：
                					<select name="gender">
               							<option value="unlimited">不限</option>
               							<option value="0">男</option>
              							<option value="1">女</option>
                					</select>
                				</td>
                				<td align="center">所在省/城市：
                					<select name="provinceCity">
														<option value="unlimited">不限</option>
															<option value="1">北京</option>
															<option value="2">上海</option>
															<option value="3">天津</option>
															<option value="4">重庆</option>
															<option value="5">辽宁</option>
															<option value="6">广东</option>
															<option value="7">浙江</option>
															<option value="8">江苏</option>
															<option value="9">山东</option>
															<option value="10">四川</option>
															<option value="11">黑龙江</option>
															<option value="12">湖南</option>
															<option value="13">湖北</option>
															<option value="14">福建</option>
															<option value="15">陕西</option>
															<option value="16">河南</option>
															<option value="17">安徽</option>
															<option value="18">河北</option>
															<option value="19">吉林</option>
															<option value="20">江西</option>
															<option value="21">广西</option>
															<option value="22">山西</option>
															<option value="23">内蒙古</option>
															<option value="24">甘肃</option>
															<option value="25">贵州</option>
															<option value="26">新疆</option>
															<option value="27">云南</option>
															<option value="28">宁夏</option>
															<option value="29">海南</option>
															<option value="30">青海</option>
															<option value="31">西藏</option>
															<option value="32">港澳台</option>
															<option value="33">海外</option>
															<option value="34">其它</option>
												</select>
												</td>
                    			<td align="center">
            						<input type="submit" name="Submit2"  value="开始检索"  />                 
          						</td>
         					</tr>
						</table>
						</form>
					</td>
				</tr>
			</tbody>		
					
			<table width="100%" cellspacing="1" cellpadding="3" align="center">
				<tfoot>
				<c:forEach items="${memberList }" var="info">
				<tr height="25">
					<td width="15%" align="center">${info.nickname }</td>
					<td width="15%" align="center">
						<c:if test="${info.gender == 0 }">男</c:if>
						<c:if test="${info.gender == 1 }">女</c:if>
					</td>
					<td width="15%" align="center">
						${info.age }
					</td>
					<td width="15%" align="center">
						<%=Util.getProvinceNameById( ((Memberinfo)pageContext.getAttribute("info")).getProvincecity() ) %>
					</td>
					<td width="20%" align="center">
						<a href="messenger/addFriend.action?friendname=${info.nickname }">添加好友</a>
					</td>
					<td width="20%" align="center">
						<a href="#">发送短信</a>
					</td>
				</tr>
				</c:forEach>
				</tfoot>
			</table>
		</table>
	     <br /><br />
        <div id="hots">
          <h2>我的地盘</h2>
          <ul>
          <li>
           <div>
            <img src="images/a.gif" />
            <a href="member/modify.jsp">基本信息</a>
            <p>可修改自己的基本信息</p>
           </div>
          </li>
          <li>
           <div>
            <img src="images/b.gif" />
            <a href="messenger/inbox.jsp">我的信箱</a>
            <p>写信息、收件箱、发件箱</p>
           </div>
          </li>
          <li>
           <div>
            <img src="images/c.gif" />
            <a href="messenger/listFriends.action">我的好友</a>
            <p>好友管理及黑名单</p>
           </div>
          </li>
          <li>
           <div>
            <img src="images/d.gif" />
            <a href="member/noSpace.jsp">个性空间</a>
            <p>创建自己的个性空间</p>
           </div>
          </li>
          </ul>
        </div> 
         
     </div>
     <div id="right">
       <h2>&nbsp;</h2>
      <ul><li></li>
      </ul>
     </div>
     <div class="clear"></div>
    
     
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
