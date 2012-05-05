package cn.edu.tongji.sse.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



public class Task implements Serializable {

	private static final long serialVersionUID = -366840727953692960L;
	
	private Long id;
	private User user;
	private Shop shop;
	private Short state;
	private Date createDate;
	private Date finishDate;
	private String fileName;
	private Short fileType;
	
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Short getFileType() {
		return fileType;
	}
	public void setFileType(Short fileType) {
		this.fileType = fileType;
	}
	
	
	
}
