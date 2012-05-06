package cn.edu.tongji.sse.gcp;

public class Client {
	private String clientId;
	private String clientSecret;
	private String redirectUrl;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {		
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	
	
}
