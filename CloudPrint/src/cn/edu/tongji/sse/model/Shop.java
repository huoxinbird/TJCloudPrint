package cn.edu.tongji.sse.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@org.hibernate.annotations.Entity(
		dynamicUpdate = true
)
public class Shop implements Serializable {

	private static final long serialVersionUID = -2605903814455581609L;

	private Long id;
	private String name;
	
	
	private String token;
	private String refresh;
	
	private User user;
	
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Transient
	public String getShopId() {
		return id.toString();
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRefresh() {
		return refresh;
	}
	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}


	
	
	
}
