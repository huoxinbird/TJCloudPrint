package cn.edu.tongji.sse.dao;

import cn.edu.tongji.sse.model.Guest;


public interface IGuestDao {
	public boolean isValid(String username, String password);

	public boolean isExist(String username);

	public void insertGuest(Guest guest);

	public Guest getGuest(String guestId);

	

	public void deleteGuest(String guestId);
}
