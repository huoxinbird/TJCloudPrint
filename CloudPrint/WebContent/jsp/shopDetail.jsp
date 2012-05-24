<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="/CloudPrint/css/home.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/page.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/header.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/footer.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/grid.css" rel="stylesheet" type="text/css">
<title>Shop Detail</title>
</head>
<body class="body">

<div id="wrap">
		<header class="page_hd">
			<nav>
				<ul>
					<li><a href="/CloudPrint/guest/home"><s:property
								value="#session.username" /></a></li>
					<li><a href="/CloudPrint/shop/home">我是打印店老板</a></li>
					<li><a href="/CloudPrint/logout">登出</a></li>
				</ul>
			</nav>

			<div class="page_meta">
				<h1>
					<a href="/CloudPrint/home">同济云打印</a>
				</h1>
			</div>

		</header>
		
		<section class="tabs">
			<ul>
				<li>
				<a class="current"><s:property value="shop.name" /></a>
				</li>
			</ul>
		</section>
		
		<form action="/CloudPrint/task/submit" enctype="multipart/form-data" method="post">
		<input type="file" name="doc" />
		<input type="submit" value="Upload"/>
		<input type="hidden" name="shopId" value="${shopId}" />
		</form>
		
		<footer class="page_ft clearfix">
			<p>
				Copyright <span class="copy_icon">&copy;</span> 2012 hxbird. All
				rights reserved.
			</p>

		</footer>
		
</div>








</body>
</html>