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

	
	public boolean register(User u) 
	{
	
		if (this.userDao.isExist(u.getUsername())) {
			return false;
		}
		
		this.userDao.insertUser(u);
		
		
		return true;
	}

	
	public boolean login(User u) 
	{		
		String un = u.getUsername();
		String pw = u.getPassword();
		if (un == null || pw == null) {
			return false;
		}
		
		User user = this.userDao.getUser(un, pw);
		if (user != null) {
			System.out.println("valid username and password with id:"+user.getId());
			u.setId(user.getId());
			return true;
		}
		
		return false;
	}

	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	
}
