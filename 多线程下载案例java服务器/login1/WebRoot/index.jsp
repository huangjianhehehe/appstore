<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <form action="/login1/LoginServlet">
    <br>使用get方式提交数据到服务器
    <br>用户名<br><input type="text" name="username">
    <br>密码<br><input type="password" name="password">
    <br>
    <input type="submit">
    
    </form>
    
     <form action="/login1/LoginServlet" method="post">
    <br>使用post方式提交数据到服务器
    <br>用户名<br><input type="text" name="username">
    <br>密码<br><input type="password" name="password">
    <br>
    <input type="submit">
    
    </form>
  </body>
</html>
