package cn.edu.tongji.sse.action;

import javax.annotation.Resource;

import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.auth.oauth2.draft10.AccessTokenRequest.AuthorizationCodeGrant;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;


import com.opensymphony.xwork2.ModelDriven;



import cn.edu.tongji.sse.gcp.Client;
import cn.edu.tongji.sse.interceptor.SessionUser;
import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.User;
import cn.edu.tongji.sse.service.IShopService;




public class ShopAction implements SessionUser, ModelDriven<Shop> {
	

	private User user;
	private Shop shop;

	private IShopService shopService;
	
	
	
	//gcp
	private String code;
	private String error;
	private Client client;
	
	@Resource
	public void setShopService(IShopService shopService) {
		this.shopService = shopService;
	}
	
	public String list() {
//		ValueStack stack = ActionContext.getContext().getValueStack();
//		System.out.println(stack.peek().getClass());
//		stack.pop();
//		System.out.println(stack.peek().getClass());
		
		if (user == null){
			return "input";
		}
		
		
		
		this.shop = shopService.getShopForUser(user);
		if (shop == null) {
			System.out.println("shop null");
		}
		
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
	
	
	public String auth() {
		if (user == null){
			return "input";
		}
		
		return "success";
	}
	
	public String token() {
		if (user == null){
			return "input";
		}
						
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

	public User getSessionUser() {
		//System.out.println("ShopAction.getUser()");
		//System.out.println("get username:"+user.getUsername());
		
		return user;
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
					"http://localhost:8080/CloudPrint/shop/token");
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
	
}
