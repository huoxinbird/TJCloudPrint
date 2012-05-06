package cn.edu.tongji.sse.action;

import java.util.List;

import javax.annotation.Resource;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.auth.oauth2.draft10.AccessTokenRequest.AuthorizationCodeGrant;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;


import com.opensymphony.xwork2.ModelDriven;



import cn.edu.tongji.sse.gcp.Client;
import cn.edu.tongji.sse.interceptor.SessionUser;
import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.Task;
import cn.edu.tongji.sse.model.User;
import cn.edu.tongji.sse.service.IShopService;




public class ShopAction implements SessionUser, ModelDriven<Shop> {
	

	private User user;
	private Shop shop;

	private IShopService shopService;
	private List<Shop> openedShops;
	private List<Task> tasks;
	
	//gcp
	private String code;
	private String error;
	private Client client;
	

	
	public String list() {
		
		openedShops = shopService.getOpenedShops();
		
		return "success";
	}
	
	public String home() {		
				
		this.shop = shopService.getShopForUser(user);
		if (shop == null) {
			System.out.println("shop null");
		}
		else {
			this.tasks = shopService.getTasksForShop(shop);
		}
		
		
		
		return "success";
	}
	
	public String open() {
		
		if (shopService.addShopForUser(user, shop))
		{
			return "success";
		}
		
		return "input";
	}
	
	
	public String auth() {

		
		return "success";
	}
	
	public String token() {

						
		if (error != null || code == null) {
			// user deny
			return "error";
		} 
		
		String token = requestAccessToken();
		if (token != null) {

			shopService.setTokenForShopOfUser(user, token);
			
			return "success";
		}
		
		return "error";
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	public Shop getShop() {
		if (shop == null) {
			System.out.println("get shop null");
		}		
		
		
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


	public void setCode(String code) {
		this.code = code;
	}

	public void setError(String error) {
		this.error = error;
	}

	private String requestAccessToken() {
		System.out.println("requestAccessToken");
		try {
			AuthorizationCodeGrant request = new AuthorizationCodeGrant(
					new NetHttpTransport(), new JacksonFactory(),
					"https://accounts.google.com/o/oauth2/token",
					client.getClientId(), client.getClientSecret(), this.code,
					client.getRedirectUrl());
			AccessTokenResponse response = request.execute();
			System.out.println("Access token: " + response.accessToken);

			//client.setToken(response.accessToken);
			return response.accessToken;

		}

		catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	@Resource
	public void setClient(Client client) {		
		this.client = client;
	}
	
	public Client getClient() {
		return this.client;
	}

	public List<Shop> getOpenedShops() {
		return openedShops;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	
	@Resource
	public void setShopService(IShopService shopService) {
		this.shopService = shopService;
	}

	
}
