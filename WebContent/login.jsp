<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	
%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="杰普软件(www.briup.com)" />
<meta name="description" content="杰普软件(www.briup.com)" />
<title>杰普——跑步社区</title>
<link rel="stylesheet" type="text/css" id="css" href="style/main.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style1.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style.css" />
<script src="js/main.js" type="text/javascript"></script>
<script src="js/base.js" type="text/javascript"></script>
       
</head>

<body onbeforeunload="RunOnBeforeUnload()" onunload="RunOnUnload()">
<c:if test="${msg!=null }">
	<script>
		alert("${msg}");
	</script>
</c:if>
	<div id="btm">
		<div id="main">

			<div id="header">
				<div id="top"></div>
				<div id="logo">
					<h1>跑步社区</h1>
				</div>
				<div id="logout">
					<a href="login.jsp">注 销</a> | 收 藏
				</div>
				<div id="mainnav">
					<ul>
						<li class="current"><a href="#">首页</a></li>
						<li><a href="other/musicrun.html">音乐跑不停</a></li>
						<li><a href="other/equip.html">跑步装备库</a></li>
						<li><a href="other/guide.html">专业跑步指南</a></li>
						<li><a href="other/bbs.html">跑步论坛</a></li>

					</ul>
					<span>
						<form onsubmit="return checkLoginForm(this);" action="loginAction!login.action" method="post" 
						 name="myform" >
						 <%--Start  Code--%>
            <div class="row">
        <div id="captcha"></div>//滑动图片验证弹出层
<script src="https://static.geetest.com/static/tools/gt.js"></script>
                <script>
                    var handler = function (captchaObj) {
                         // 将验证码加到id为captcha的元素里
                         captchaObj.appendTo("#captcha");//弹出层
                         captchaObj.bindOn("#loginSubmit");//弹出模式指定的触发事件
                     };
                    $.ajax({
                        // 获取id，challenge，success（是否启用failback）
                        url: "https://sso.yoju360.com/gt/init.do",//将调用这个接口进行初始化，这是我本地方法。后面会有介绍如何使用，若是http协议，直接改为http即可
                        type: "get",
                        dataType: "json", // 使用jsonp格式
                        success: function (data) {
                            if(data!=null){
                                // 使用initGeetest接口
                                // 参数1：配置参数，与创建Geetest实例时接受的参数一致
                                // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
                                initGeetest({
                                    gt: data.gt,
                                    challenge: data.challenge,
                                    product: "popup", // 产品形式 弹出
                                    https : true,
                                    offline: !data.success
                                }, handler);
                            }
                        }
                    });
                </script>
							<table width="100%">
								<tr>
									<td align="right">用户名： <input type="text" id="userName" name="memberinfo.nickname"
										style="width: 80px" />
									</td>
									<td>密码： <input type="password" id="passwd" name="memberinfo.password"
										style="width: 80px; height: 15px" />
									</td>
									<td>验证码： <input type="text" name="authCode"
										style="width: 80px" />
									</td>
									<td><img src="authImg" style="height:20px; width:60px"/>
									</td>
									<td><a href="javascript:location.reload();">看不清？</a></td>
									<td><font color="red">自动登录</font> <input type="checkbox"
										name="autoLogin" value="0" /></td>
									<td>
								<!-- 	<a  href="javascript:document.myform.submit();" 
										onmouseout="MM_swapImgRestore()"
										onmouseover="MM_swapImage('login','','images/login-24.gif',1)">
											<img src="images/login.gif" name="login" border="0"
											id="login" />
											
									</a> -->
									<input type="image" src="images/login.gif"/>
									</td>
									<td><a href="register.jsp"
										onmouseout="MM_swapImgRestore()"
										onmouseover="MM_swapImage('register','','images/reg-21.gif',1)">
											<img src="images/reg_v2.gif" name="register" border="0"
											id="register" />
									</a></td>

									<td><a href="register.html">如何注册</a> ｜ <a
										href="passwd_missing.jsp"> 忘记密码</a></td>
								</tr>
							</table>
						</form>
					</span>
				</div>
			</div>

			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<div id="content">


				<div id="left">

					<div id="tabs0">
						<ul class="menu0" id="menu0">
							<li onmouseover="setTab(0)" class="lisovers">社区新闻1</li>
							<li onmouseover="setTab(1)">社区新闻2</li>
							<li onmouseover="setTab(2)">社区新闻3</li>
							<li onmouseover="setTab(3)">社区新闻4</li>
						</ul>
						<div class="main0" id="main0">
							<div class="block">
								<img src="images/net.jpg" />

								<p>1999年,盖茨撰写了《未来时速:数字神经系统和商务新思维》（ Business @ the Speed of
									Thought: Using a Digital Nervous System）一书，
									向人们展示了计算机技术是如何以崭新的方式来解决商业问题的。这本书在超过60个国家以25种语言出版。</p>

							</div>
							<div>
								<img src="images/php.jpg" />

								<p>
									除了对计算机和软件的热爱之外，盖茨对生物技术也很有兴趣。他是ICOS公司董事会的一员，这是一家专注于蛋白质基体及小分子疗法的公司。他也是很多其它生物技术公司的投资人
								</p>

							</div>
							<div>
								<img src="images/net.jpg" />

								<p>对于盖茨来说，慈善事业也是非常重要的。他和他的妻子Melinda已经捐赠了近
									58亿美元建立了一个基金，支持在全球医疗健康和教育领域的慈善事业，希望随着人类进入21世纪，这些关键领域的科技进步能使全人类都受益
								</p>
							</div>
							<div>
								<img src="images/php.jpg" />

								<p>到今天为止，盖茨和他的妻子Melinda
									Gates建立的基金已经将25多亿美元用于了全球的健康事业，将14亿多美元用于改善人们的学习条件，其中包括为盖茨图书馆购置计算机设备、为美国和加拿大的低收入社区的公共图书馆提供互联网培训和互联网访问服务
								</p>
							</div>
						</div>
						<div class="clear"></div>
					</div>

					<div id="hots">
						<h2>我的地盘</h2>
						<ul>
							<li>
								<div>
									<img src="images/a.gif" /> <a href="member/modify.jsp">基本信息</a>
									<p>可修改自己的基本信息</p>
								</div>
							</li>
							<li>
								<div>
									<img src="images/b.gif" /> <a href="../messenger/inbox.html">我的信箱</a>
									<p>写信息、收件箱、发件箱</p>
								</div>
							</li>
							<li>
								<div>
									<img src="images/c.gif" /> <a
										href="../messenger/buddyList.html">我的好友</a>
									<p>好友管理及黑名单</p>
								</div>
							</li>
							<li>
								<div>
									<img src="images/d.gif" /> <a href="member/noSpace.html">个性空间</a>
									<p>创建自己的个性空间</p>
								</div>
							</li>
						</ul>
					</div>

				</div>

				<div id="right">
					<table width="300">
						<tr>
							<td align="left">您是本社区的第10位来访者</td>
						</tr>

					</table>
					<div id="demo">
						<div id="indemo">
							<div id="demo1">
								<a href="#"><img src="images/1.gif" border="0" width="90"
									height="80" /></a> <a href="#"><img src="images/2.gif"
									border="0" width="90" height="80" /></a> <a href="#"><img
									src="images/3.gif" border="0" width="90" height="80" /></a> <a
									href="#"><img src="images/4.gif" border="0" width="90"
									height="80" /></a> <a href="#"><img src="images/1.gif"
									border="0" width="90" height="80" /></a> <a href="#"><img
									src="images/2.gif" border="0" width="90" height="80" /></a> <a
									href="#"><img src="images/3.gif" border="0" width="90"
									height="80" /></a> <a href="#"><img src="images/4.gif"
									border="0" width="90" height="80" /></a>
							</div>
							<div id="demo2"></div>
						</div>
					</div>
					<h2>积分排行榜</h2>
					<ul>
						<li><a href="#">Briup1</a></li>
						<li><a href="#">Briup2</a></li>
						<li><a href="#">Briup3</a></li>
						<li><a href="#">Briup4</a></li>
						<li><a href="#">Briup5</a></li>
						<li><a href="#">Briup6</a></li>
						<li><a href="#">Briup7</a></li>
						<li><a href="#">Briup8</a></li>
						<li><a href="#">Briup9</a></li>
						<li><a href="#">Briup10</a></li>
					</ul>
				</div>
				<div class="clear"></div>

			</div>

			<div id="footer">
				<div id="copyright">
					<div id="copy">
						<p>CopyRight&copy;2009</p>
						<p>跑步社区(www.irun.com)</p>
					</div>
				</div>
				<div id="bgbottom"></div>
			</div>

		</div>
	</div>
</body>
</html>

<html></html>
<html></html>
<html></html>
<html></html>
<html></html>
<html></html>
<html></html>
