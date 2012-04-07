package cn.edu.tongji.sse.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Guest implements Serializable {

	private static final long serialVersionUID = -6925661679308746598L;

	private Long id;
	private String name;
	
	public Guest() { }
	
	
	@Id 
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
	
	
}
