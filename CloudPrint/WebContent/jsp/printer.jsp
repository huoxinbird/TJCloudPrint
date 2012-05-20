<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>printer.jsp</title>
</head>
<body>
hikhjjljkl
<s:iterator value="printers" status="status">
<div>
<hr>
<a href="./printsettings?printerId=${id}">select</a> <br>
<s:property value="id" /> <br>
<s:property value="name" />
</div>

</s:iterator>

</body>
</html>