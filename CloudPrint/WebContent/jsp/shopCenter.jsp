<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<s:property value="user.username"/> <br>

<s:if test="shop == null">

<form method="post" action="/CloudPrint/shop/open" >
<label>Shop Name
<input type="text" name="shopName" />
</label>
<input type="submit" value="open" />
</form>

	
</s:if>
<s:else>
	<s:property value="shop.name"/>
</s:else>

</body>
</html>