package cn.edu.tongji.sse.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.api.client.auth.oauth2.draft10.AccessTokenErrorResponse;
import com.google.api.client.auth.oauth2.draft10.AccessTokenRequest.AuthorizationCodeGrant;
import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.http.HttpResponseException;

import cn.edu.tongji.sse.admin.Client;

public class AdminAction implements ServletRequestAware {

	private String code;
	private HttpServletRequest servletRequest;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	
	public String authorize() {
		String hostName = this.servletRequest.getHeader("Host");
		System.out.println(hostName);
		

		
		
		return "success";
	}
	
	static void requestAccessToken(String code, String redirectUrl) throws IOException {
	    ApplicationContext context = new ClassPathXmlApplicationContext("account.xml");
				
		Client client = (Client) context.getBean("client");  
		
		try {
	        AuthorizationCodeGrant request = new AuthorizationCodeGrant();
	        request.authorizationServerUrl = "https://accounts.google.com/o/oauth2/token";
	        request.clientId = client.getClientId();
	        request.clientSecret = client.getClientSecret();
	        request.code = code;
	        request.redirectUri = redirectUrl;
	        
	        AccessTokenResponse response = request.execute().parseAs(AccessTokenResponse.class);
	        System.out.println("Access token: " + response.accessToken);
	      } catch (HttpResponseException e) {
	        AccessTokenErrorResponse response = e.response.parseAs(AccessTokenErrorResponse.class);
	        System.out.println("Error: " + response.error);
	      }
	    }
	
}
