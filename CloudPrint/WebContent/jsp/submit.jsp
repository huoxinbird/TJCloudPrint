<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<form action="/CloudPrint/task/submit" enctype="multipart/form-data" method="post">
<input type="file" name="doc" />
<input type="submit" value="Upload"/>
<input type="hidden" name="shopId" value="${shopId}" />
</form>

</body>
</html>