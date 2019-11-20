<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function _change() {
	    var ele = document.getElementById("vCode");
	    ele.src="<c:url value='/VerifyCodeServlet'/>?xxx="+new Date().getTime();
    }
</script>
	  <%--<script type="text/javascript">--%>
		  <%--function _change() {--%>
		      <%--var ele= document.getElementById("vCode");--%>
		      <%--ele.src="<c:url value='/VerifyCodeServlet'/>?xxx="+new Date().getTime();--%>
          <%--}--%>
	  <%--</script>--%>
  </head>
  
  <body>
  <h1>注册</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="<c:url value='/UserServlet'/>" method="post">
	<input type="hidden" name="method" value="regist"/>
	用户名：<input type="text" name="username" value="${form.username}"/>${error.username}<br/>
	密　码：<input type="password" name="password" value="${form.password}"/>${error.password}<br/>
	验证码：<input type="text" name="verifyCode" value="${form.verifyCode}" size="3"/>
	<img id="vCode" src="<c:url value='/VerifyCodeServlet'/>" border="2"/>
	<a href="javascript:_change()">换一张</a>${error.verifyCode}<br>
	<input type="submit" value="注册"/>
</form>
  </body>
<%--<from action="<c:url value='/UserServlet'/>" method="post">--%>
	<%--用户名：<input type="text" name="username" value="${form.username}"/>${error.username}--%>
	<%--密  码：<input type="password" name="password" value="${form.password}"/>${error.password}--%>
	<%--验证码：<input type="text" name="verifyCode" value=""/>--%>
	<%--<img id="vCode" src="<c:url value='/VerifyCodeServlet'/>" border="2"/>--%>
	<%--<a href="javascript:_change()">换一张</a>--%>
	<%--<input type="submit" value="注册"/>--%>
<%--</from>--%>
</html>
