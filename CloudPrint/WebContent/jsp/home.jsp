<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>home</title>
</head>
<body>
welcome, <s:property value="user.username"/> !

<a href="/CloudPrint/shop/list">我是打印店老板</a>
<a href="/CloudPrint/">我是打印店顾客</a>

<a href="/CloudPrint/logout">登出</a>

</body>
</html>