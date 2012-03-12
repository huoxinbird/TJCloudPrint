package cn.edu.tongji.sse.action;

import java.io.IOException;

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

	ApplicationContext context;
	
	
	public AdminAction() {
		super();
		// TODO Auto-generated constructor stub
		this.context = new ClassPathXmlApplicationContext("account.xml");
				
		
	}

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
		System.out.println(this.code);
		
		if (hostName.startsWith("localhost")) {
			System.out.println("startsWith");
			//requestAccessToken(this.code);
		}
		else {
			return "error";
		}
		
		
		return "success";
	}
	
	public String login() {
		
		
		return "success";
		
	}
	
	private void requestAccessToken(String code) throws IOException {
		
		Client client = (Client) context.getBean("client");

		try {
			AuthorizationCodeGrant request = new AuthorizationCodeGrant();
			request.authorizationServerUrl = "https://accounts.google.com/o/oauth2/token";
			request.clientId = client.getClientId();
			request.clientSecret = client.getClientSecret();
			request.code = code;
			request.redirectUri = "http://localhost";

			AccessTokenResponse response = request.execute();
			System.out.println("Access token: " + response.accessToken);
			
		} catch (HttpResponseException e) {
			AccessTokenErrorResponse response = e.getResponse()
					.parseAs(AccessTokenErrorResponse.class);
			System.out.println("Error: " + response.error);
		}
	}

}
