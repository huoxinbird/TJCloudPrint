package cn.edu.tongji.sse.action;



import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;




import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ModelDriven;

import cn.edu.tongji.sse.interceptor.SessionUser;
import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.User;
import cn.edu.tongji.sse.service.IShopService;
import cn.edu.tongji.sse.service.IUserService;

public class UserAction implements ServletRequestAware, SessionUser, ModelDriven<User> {
			
	private IUserService userService;	
	private IShopService shopService;
	private User user;
	private List<Shop> openedShops;	
	private HttpServletRequest request;		
	private Long shopId;
	private Shop shop;
	
	final static String USERNAME = "username";
	final static String PASSWORD = "password";
	final static String USERID = "userId";
	

	


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
				
		user = userService.login(user.getUsername(),user.getPassword());
		
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
		
		
		if (userService.register(user.getUsername(),user.getPassword())) {
			return "success";
		}
		

		return "input";
	}
	
	
	public String home() {
				
		
		openedShops = shopService.getOpenedShops();
		
		return "success";
	}
	
	public String shopDetail() {
		shop = shopService.getShop(shopId);
		
		return "success";
	}

	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}


	public void setSessionUser(User u) {
		this.user = u;
	}

	public List<Shop> getOpenedShops() {
		return openedShops;
	}

	
	public User getModel() {
		user = new User();
		
		return user;
	}


	@Resource
	public void setShopService(IShopService shopService) {
		this.shopService = shopService;
	}
	
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
