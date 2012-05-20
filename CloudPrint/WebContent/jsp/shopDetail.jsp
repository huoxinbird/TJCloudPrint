<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Shop Detail</title>
</head>
<body>

<s:property value="shop.name" />

<form action="/CloudPrint/task/submit" enctype="multipart/form-data" method="post">
<input type="file" name="doc" />
<input type="submit" value="Upload"/>
<input type="hidden" name="shopId" value="${shopId}" />
</form>

</body>
</html>