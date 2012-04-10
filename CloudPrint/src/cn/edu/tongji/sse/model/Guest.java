package cn.edu.tongji.sse.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Guest implements Serializable {

	private static final long serialVersionUID = -6925661679308746598L;

	private Long id;
	private String username;
	private String password;
	
	public Guest() { }
	
	
	@Id 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
