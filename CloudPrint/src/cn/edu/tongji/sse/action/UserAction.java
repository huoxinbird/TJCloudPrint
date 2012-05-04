package cn.edu.tongji.sse.action;



import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;




import org.apache.struts2.interceptor.ServletRequestAware;

import cn.edu.tongji.sse.interceptor.SessionUser;
import cn.edu.tongji.sse.model.User;
import cn.edu.tongji.sse.service.IUserService;

public class UserAction implements ServletRequestAware, SessionUser {
			
	private IUserService userService;
	private User user;
	
	private String username;
	private String password;
	
	
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
		

		
		
		request.getSession().removeAttribute(USERNAME);
		request.getSession().removeAttribute(PASSWORD);
		request.getSession().removeAttribute(USERID);
		
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
				
		user = userService.login(username,password);
		
		if (user != null) {			
			

			
			request.getSession().setAttribute(USERNAME, user.getUsername());
			request.getSession().setAttribute(PASSWORD, user.getPassword());
			request.getSession().setAttribute(USERID, user.getUserId());
			
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
		
		
		if (userService.register(username,password)) {
			return "success";
		}
		

		return "input";
	}
	
	
	public String home() {
		
		if (user != null){
			
			return "success";
		}
		
		return "input";
	}

	


	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public User getUser() {
		return user;
	}

	public void setSessionUser(User u) {
		this.user = u;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	





}
