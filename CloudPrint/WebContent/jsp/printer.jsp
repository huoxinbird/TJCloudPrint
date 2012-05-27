<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Select Printer</title>

<link href="/CloudPrint/css/page.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/header.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/footer.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/grid.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/selectPrinter.css" rel="stylesheet" type="text/css">
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
				<a class="current">选择打印机</a>
				</li>
			</ul>
		</section>

		<div id="printer_grid">
			<div class="grid clearfix">
				<s:iterator value="printers" status="status">
				
					<article class="grid_cell" onclick="location.href='/CloudPrint/shop/printsettings?printerId=${id}';">
						

<h2><s:property value="name" /></h2>
<img src="/CloudPrint/img/printer.png" height="100" width="100" />

					</article>
				</s:iterator>

			</div>

		</div>

		<footer class="page_ft clearfix">
			<p>
				Copyright <span class="copy_icon">&copy;</span> 2012 hxbird. All
				rights reserved.
			</p>

		</footer>
	</div>




</body>
</html>