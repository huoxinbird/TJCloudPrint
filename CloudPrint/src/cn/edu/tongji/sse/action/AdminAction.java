package cn.edu.tongji.sse.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.google.api.client.auth.oauth2.draft10.AccessTokenErrorResponse;
import com.google.api.client.auth.oauth2.draft10.AccessTokenRequest.AuthorizationCodeGrant;
import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;

import cn.edu.tongji.sse.admin.*;

public class AdminAction implements ServletRequestAware {

	private String code;
	private HttpServletRequest servletRequest;
	private Client client;
	private String error;
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
		
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	
	public String login() {
		String hostName = this.servletRequest.getHeader("Host");
		System.out.println(hostName);
		if (hostName.startsWith("localhost")) {
			System.out.println("startsWith");
			client = ClientManager.getInstance().getClient();
			System.out.println("login:"+client.getClientId());
			return "success";
		}
		else {
			return "error";
		}
		
	}
	
	public String authorize() {
		System.out.println("authorize");
		String hostName = this.servletRequest.getHeader("Host");
		if (hostName.startsWith("localhost")) {
			if (error != null || code == null) {
				//user deny
				
			}
			else {
				//get code success
				client = ClientManager.getInstance().getClient();
				
				boolean successs = requestAccessToken();
				if (successs) {
					
					return "success";
				}
				
			}
			
			
		}
		
	
		return "error";		
				
		
	}
	
    private boolean requestAccessToken() {
    	System.out.println("requestAccessToken");
	      try {
	        AuthorizationCodeGrant request = new AuthorizationCodeGrant(new NetHttpTransport(),
	            new JacksonFactory(),
	            "https://accounts.google.com/o/oauth2/token",
	            client.getClientId(),
	            client.getClientSecret(),
	            this.code,
	            "http://localhost:8080/CloudPrint/admin/authorize");
	        AccessTokenResponse response = request.execute();
	        System.out.println("Access token: " + response.accessToken);
	        
	        client.setToken(response.accessToken);
	        return true;
	      
	      } catch (HttpResponseException e) {
	        
	    	  AccessTokenErrorResponse response;
			try {
				response = e.getResponse().parseAs(AccessTokenErrorResponse.class);
				System.out.println("Error: " + response.error);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	      
	    	  
	    	  return false;
	      } catch (IOException e) {
			// TODO: handle exception
	    	  return false;
		}

	      
	    }
	
}
