<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
welcome, <s:property value="#session.username" />  
<a href="../home">主页</a> <br>

<s:iterator value="guestTasks" status="status">
<div>

fileName: <s:property value="fileName" /> <br>
state: <s:property value="stateName" /> <br>
createDate: <s:property value="createDateString" /> <br><br>

</div>
        
</s:iterator>


</body>
</html>