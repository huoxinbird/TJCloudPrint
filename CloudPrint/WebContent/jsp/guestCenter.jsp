<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><s:property value="#session.username" /></title>
<link href="/CloudPrint/css/home.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/page.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/header.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/footer.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/grid.css" rel="stylesheet" type="text/css">
<link href="/CloudPrint/css/taskCell.css" rel="stylesheet" type="text/css">
<script src="/CloudPrint/js/jquery-1.7.2.min.js"></script>
<script src="/CloudPrint/js/task.js"></script>

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
				<a  id="waitingtab" class="current">等待处理的打印任务</a>
				</li>
				<li>
				<a  id="donetab" >可领取的打印任务</a>
				</li>
			</ul>
		</section>

		<div id="task_grid">
			<div class="grid clearfix">
				<s:iterator value="guestTasks" status="status">

<s:if test="stateName == 'waiting'">

<article class="grid_cell waiting_task">
<h4 class="user_name"><s:property value="shop.name" /></h4>
<h5 class="create_date"><s:property value="createDateString" /></h5>
<h5 class="create_time"><s:property value="createTimeString" /></h5>

<hr>						
						
						<s:if test="fileTypeName == 'application/msword'">
						<img src="/CloudPrint/img/word.png" height="64" width="64" /> 
						</s:if>
						<s:else>
						<img src="/CloudPrint/img/pdf.png" height="64" width="64" />
						</s:else>
						
						<h2><s:property value="fileName" /></h2>
						
						

					</article>

</s:if>
<s:else>
			

<article class="grid_cell done_task">
<h4 class="user_name"><s:property value="shop.name" /></h4>	
						<h5 class="create_date"><s:property value="createDateString" /></h5>
						<h5 class="create_time"><s:property value="createTimeString" /></h5>
						<hr>
						
						<s:if test="fileTypeName == 'application/msword'">
						<img src="/CloudPrint/img/word.png" height="64" width="64" /> 
						</s:if>
						<s:else>
						<img src="/CloudPrint/img/pdf.png" height="64" width="64" />
						</s:else>
						
						<h2><s:property value="fileName" /></h2>

					</article>

</s:else>		
					
					
					
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