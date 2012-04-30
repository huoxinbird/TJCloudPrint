package cn.edu.tongji.sse.service;

import javax.annotation.Resource;



import org.springframework.stereotype.Component;


import cn.edu.tongji.sse.dao.IUserDao;
import cn.edu.tongji.sse.model.User;

@Component
public class UserService implements IUserService {

	private IUserDao userDao;

	public boolean isValid(User u)
	{
		String un = u.getUsername();
		String pw = u.getPassword();
		if (un == null || pw == null) {
			return false;
		}
		return userDao.isValid(u);
	}

	
	public boolean register(String username, String password) 
	{
	
		if (this.userDao.isExist(username)) {
			return false;
		}
		
		User u = new User();
		u.setPassword(password);
		u.setUsername(username);
		
		this.userDao.insertUser(u);		
		
		return true;
	}

	
	public User login(String username, String password)
	{		
		if (username == null || password == null) {
			return null;
		}
		
		User user = this.userDao.getUser(username, password);
		
		
		return user;
	}

	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	
}
