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
	
	final static String USERNAME = "username";
	final static String PASSWORD = "password";
	final static String USERID = "userId";
	
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String logout() {
		System.out.println("UserAction.logout()");
		
		//remove cookie
		Cookie usernameCookie = new Cookie(USERNAME, "");
		usernameCookie.setMaxAge(0);
		Cookie passwordCookie = new Cookie(PASSWORD, "");
		passwordCookie.setMaxAge(0);
		Cookie useridCookie = new Cookie(USERID, "");
		useridCookie.setMaxAge(0);
		response.addCookie(usernameCookie);
		response.addCookie(passwordCookie);
		response.addCookie(useridCookie);
		
		return "success";
	}
	
	private boolean isPostMethod() {
		String method = request.getMethod();
		boolean isPostMethod = method.equalsIgnoreCase("POST");
		return isPostMethod;
	}
	
	public String login() {
		if (!isPostMethod()) {
			return "input";
		}
				
		
		if (this.userService.login(this.user)) {			
			
			//add cookie
			response.addCookie(new Cookie(USERNAME, this.user.getUsername()));
			response.addCookie(new Cookie(PASSWORD, this.user.getPassword()));			
			response.addCookie(new Cookie(USERID, this.user.getId().toString()));
			
			return "success";
		}
		else {
			return "input";
		}
		
		
	}

	public String register() {
		if (!isPostMethod()) {
			return "input";
		}
		
		System.out.println("username: "+user.getUsername());
		System.out.println("password: "+user.getPassword());
		
		if (userService.register(user)) {
			return "success";
		}
		

		return "input";
	}
	
	
	public String home() {
		
		if (userService.isValid(user)){
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
