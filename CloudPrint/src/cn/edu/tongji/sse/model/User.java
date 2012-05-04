package cn.edu.tongji.sse.model;

import java.io.Serializable;


import javax.persistence.*;

@Entity
@org.hibernate.annotations.Entity(
		dynamicUpdate = true
)
public class User implements Serializable {

	private static final long serialVersionUID = -6925661679308746598L;

	private Long id;
	private String username;
	private String password;
//	private List<Shop> shops = new ArrayList<Shop>();
	
	
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUserId(String id) {
		System.out.println("User.setUserId()"+id);
		this.id = Long.parseLong(id);
	}
	
	@Transient
	public String getUserId() {
		return this.id.toString();
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

//	@OneToMany(mappedBy = "user")
//	public List<Shop> getShops() {
//		return shops;
//	}
//
//
//	public void setShops(List<Shop> shops) {
//		this.shops = shops;
//	}

	
    
	
	

	
	
}
