package cn.edu.tongji.sse.action;



import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ModelDriven;

import cn.edu.tongji.sse.model.User;
import cn.edu.tongji.sse.service.IUserService;

public class UserAction implements ModelDriven<User>, ServletResponseAware, ServletRequestAware {
			
	private IUserService userService;
	private User user;
	

	private HttpServletResponse response;
	private HttpServletRequest request;		
	
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String logout() {
		System.out.println("UserAction.logout()");
		Cookie usernameCookie = new Cookie("username", "");
		usernameCookie.setMaxAge(0);
		Cookie passwordCookie = new Cookie("password", "");
		passwordCookie.setMaxAge(0);
		response.addCookie(usernameCookie);
		response.addCookie(passwordCookie);
		
		return "success";
	}
	
	public String login() {
		if (user.getUsername() == null) {
			return "input";
		}
		
		
		if (this.userService.login(this.user)) {			
			
			response.addCookie(new Cookie("username", this.user.getUsername()));
			response.addCookie(new Cookie("password", this.user.getPassword()));
			
			return "success";
		}
		else {
			return "input";
		}
		
		
	}

	public String register() {
		
		System.out.println("username: "+user.getUsername());
		System.out.println("password: "+user.getPassword());
		this.userService.register(this.user);
		
		
		
		return "success";
	}
	
	
	public String home() {
		user = userService.userWithCookie(request.getCookies());
		
		if (user == null) {
			return "input";
		}
		
		if (userService.login(user)){
			return "success";
		}
		
		return "input";
	}

	
	public User getModel() {
		System.out.println("UserAction.getModel()");
		
		this.user = new User();
		
		return this.user;
	}

	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public User getUser() {
		return user;
	}



}
