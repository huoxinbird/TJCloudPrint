package cn.edu.tongji.sse.action;



import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;


import com.google.api.client.auth.oauth2.draft10.AccessTokenRequest.AuthorizationCodeGrant;
import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;


import cn.edu.tongji.sse.gcp.*;

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

	@Resource
	public void setClient(Client client) {
		this.client = client;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	private boolean isRequestFromLocalhost() {
		
		String hostName = this.servletRequest.getHeader("Host");
						
		return hostName.startsWith("localhost");					
	}
	
	public String login() {
		

		if (isRequestFromLocalhost()) {			
			return "success";
		} 
		else {
			return "error";
		}

	}

	public String authorize() {

		if (isRequestFromLocalhost()) {
			if (error != null || code == null) {
				// user deny

			} 
			else {
				// get code success				

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
			AuthorizationCodeGrant request = new AuthorizationCodeGrant(
					new NetHttpTransport(), new JacksonFactory(),
					"https://accounts.google.com/o/oauth2/token",
					client.getClientId(), client.getClientSecret(), this.code,
					"http://localhost:8080/CloudPrint/admin/authorize");
			AccessTokenResponse response = request.execute();
			System.out.println("Access token: " + response.accessToken);

			client.setToken(response.accessToken);
			return true;

		}

		catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

}
