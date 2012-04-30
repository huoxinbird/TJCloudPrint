package cn.edu.tongji.sse.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;

import cn.edu.tongji.sse.interceptor.SessionUser;
import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.User;
import cn.edu.tongji.sse.service.IShopService;




public class ShopAction implements SessionUser, ModelDriven<Shop> {
	
	private User user;
	private Shop shop;

	private IShopService shopService;
	
	
	
	

	
	@Resource
	public void setShopService(IShopService shopService) {
		this.shopService = shopService;
	}
	
	public String list() {
		
		if (user == null){
			return "input";
		}
		
		this.shop = shopService.getShopForUser(user);
		System.out.println("list username:"+user.getUsername());
		return "success";
	}
	
	public String open() {
		if (user == null){
			return "input";
		}
		
		
		if (shopService.addShopForUser(user, shop))
		{
			return "success";
		}
		
		return "input";
	}

	public User getSessionUser() {
		System.out.println("ShopAction.getUser()");
		System.out.println("get username:"+user.getUsername());
		
		return user;
	}


	public Shop getShop() {
		System.out.println("get shop name:"+shop.getName());
		
		return shop;
	}



	
	public void setSessionUser(User user) {
		System.out.println("set username:"+user.getUsername());
		this.user = user;
		
	}

	
	public Shop getModel() {
		this.shop = new Shop();
		
		return shop;
	}

	

	
}
