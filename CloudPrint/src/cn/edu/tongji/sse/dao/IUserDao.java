package cn.edu.tongji.sse.dao;

import cn.edu.tongji.sse.model.User;


public interface IUserDao {
	public User getUser(String username, String password);

	public boolean isExist(String username);

	public void insertUser(User user);

	public User getUser(String userId);

	public boolean isValid(User user);

	public void deleteUser(String userId);
}
