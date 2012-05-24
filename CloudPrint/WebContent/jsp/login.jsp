<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>login</title>
	<link href="/CloudPrint/css/page.css" rel="stylesheet" type="text/css">
	<link href="/CloudPrint/css/header.css" rel="stylesheet" type="text/css">
	<link href="/CloudPrint/css/login.css" rel="stylesheet" type="text/css">	
	<link href="/CloudPrint/css/animate-custom.css" rel="stylesheet" type="text/css" >
</head>
<body class="body">
	<div id="wrap">
		<header class="page_hd">


			<div class="page_meta">
				<h1>
					<a href="">同济云打印</a>
				</h1>
			</div>

		</header>

		<a class="hiddenanchor" id="toregister"></a>
		<a class="hiddenanchor" id="tologin"></a>
		<div id="form_wrapper">
			<div id="login" class="animate form">
				<form method="post" action="/CloudPrint/login"> 
					<h3>登录</h3> 
					<div class="input_wrap"> 
						<label for="password"> 用户名 </label>
						<input id="username" name="username" required="required" type="text" />
					</div>
					<div class="input_wrap"> 
						<label for="password"> 密码 </label>
						<input id="password" name="password" required="required" type="password"  /> 
					</div>
					<div>
						<input class="submit" type="submit" value="登录"/>

					</div>
					<p class="change_link">
						还未注册？
						<a class="to_register" href="#toregister">去注册</a>
					</p>
				</form>
			</div>

			<div id="register" class="animate form">
				<form method="post" action="/CloudPrint/register"> 
					<h3>注册</h3> 
					<div class="input_wrap"> 
						<label for="username"> 用户名 </label>
						<input id="username" name="username" required="required" type="text" />
					</div>
					<div class="input_wrap"> 
						<label for="password"> 密码 </label>
						<input id="password" name="password" required="required" type="password"  /> 
					</div>
					<div>
						<input class="submit" type="submit" value="注册"/>

					</div>
					<p class="change_link">
						已有账号？
						<a class="to_register" href="#tologin">去登录</a>
					</p>
				</form>
			</div>
		</div>

	</div>	
</body>
</html>