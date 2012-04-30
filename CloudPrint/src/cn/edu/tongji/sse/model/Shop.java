package cn.edu.tongji.sse.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Shop implements Serializable {

	private static final long serialVersionUID = -2605903814455581609L;

	private Long id;
	private String name;
	
	
//	private String userId;
	
	private User user;
	
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
//	@Column(name="user_id")
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}


	
	
	
}
