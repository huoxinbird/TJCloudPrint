package cn.edu.tongji.sse.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;

import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.User;
import cn.edu.tongji.sse.service.IShopService;
import cn.edu.tongji.sse.service.IUserService;



public class ShopAction implements ModelDriven<User> {
	
	private User user;
	private Shop shop;
	private IUserService userService;
	private IShopService shopService;
	
	private String shopName;
	
	
	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Resource
	public void setShopService(IShopService shopService) {
		this.shopService = shopService;
	}
	
	public String list() {
		System.out.println("ShopAction.list()");
		if (!userService.isValid(user)){
			return "input";
		}
		
		this.shop = shopService.getShopForUser(user);
		
		return "success";
	}
	
	public String open() {
		if (!userService.isValid(user)){
			return "input";
		}
		
		shop = new Shop();
		shop.setName(shopName);
		
		if (shopService.addShopForUser(user, shop))
		{
			return "success";
		}
		
		return "input";
	}

	public User getUser() {
		return user;
	}

	public User getModel() {		
		
		this.user = new User();
		
		return this.user;
	}

	public Shop getShop() {
		return shop;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	

	
}
