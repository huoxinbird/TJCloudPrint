package cn.edu.tongji.sse.gcp;

public class Client {
	private String clientId;
	private String clientSecret;

	private String token;
	
	public synchronized String getToken() {
		return token;
	}
	public synchronized void setToken(String token) {
		this.token = token;
	}
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

	
	
}
