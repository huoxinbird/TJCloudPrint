package cn.edu.tongji.sse.action;



import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ModelDriven;

import cn.edu.tongji.sse.model.User;
import cn.edu.tongji.sse.service.IUserService;

public class UserAction implements ModelDriven<User>, ServletRequestAware {
	
	private IUserService userService;
	private User user;
	private HttpServletRequest request;
			
	
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String login() {
		
		if (this.userService.login(this.user)) {
			//Cookie[] cookies = this.request.getCookies();
			
			
			
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
	
	
//	public void setGuest(Guest guest) {
//		
//		System.out.println("username: "+guest.getUsername());
//		System.out.println("password:"+guest.getPassword());
//		
//		this.guest = guest;
//	}

	
	public User getModel() {
		System.out.println("UserAction.getModel()");
		
		this.user = new User();
		
		return this.user;
	}

	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}


}
