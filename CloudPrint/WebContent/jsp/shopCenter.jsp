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
	welcome,
	<s:property value="sessionUser.username" />
	<br>



 
	<s:if test="shop == null">

		<form method="post" action="/CloudPrint/shop/open">
			 
			<input type="text" name="name" />
			 
			<input type="submit" value="open" />
		</form>


	</s:if>
	<s:else>

shop name:	<s:property value="shop.name" />
		<br>

		<s:if test="shop.token == null">

			<a href="/CloudPrint/shop/auth">Authorize</a>

		</s:if>
		<s:else>
token: <s:property value="shop.token" />
		</s:else>

	</s:else> 

</body>
</html>