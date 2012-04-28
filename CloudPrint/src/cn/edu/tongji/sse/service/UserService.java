package cn.edu.tongji.sse.service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;


import org.springframework.stereotype.Component;


import cn.edu.tongji.sse.dao.IUserDao;
import cn.edu.tongji.sse.model.User;

@Component
public class UserService implements IUserService {

	private IUserDao userDao;
	
	public User userWithCookie(Cookie[] cookies)
	{
		String username = null;
		String password = null;

		if (cookies == null) return null;
		
		for (int i = 0; i < cookies.length; i++) 
		{
			Cookie thisCookie = cookies[i];
			if (thisCookie.getName().equals("username")) {					
				username = thisCookie.getValue();

			}				
			else if (thisCookie.getName().equals("password")) {
				password = thisCookie.getValue();

			}

		}

		if (username==null || password==null) {
			return null;
		}
		
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		return u;
	}
	
	public boolean isUserLoggedIn(Cookie[] cookies)
	{		
		String username = null;
		String password = null;

		if (cookies == null) return false;

		for (int i = 0; i < cookies.length; i++) 
		{
			Cookie thisCookie = cookies[i];
			if (thisCookie.getName().equals("username")) {					
				username = thisCookie.getValue();

			}				
			else if (thisCookie.getName().equals("password")) {
				password = thisCookie.getValue();

			}

		}

		if (username==null || password==null) {
			return false;
		}

		if (this.userDao.isValid(username, password)) {
			System.out.println("valid username and password");
			return true;
		}
		

		
		
		return false;
	}
	
	public boolean register(User g) 
	{
	
		if (this.userDao.isExist(g.getUsername())) {
			return false;
		}
		
		this.userDao.insertUser(g);
		
		
		return true;
	}

	
	public boolean login(User g) 
	{		
		if (this.userDao.isValid(g.getUsername(), g.getPassword())) {
			System.out.println("valid username and password");
			return true;
		}
		
		return false;
	}

	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	
}
