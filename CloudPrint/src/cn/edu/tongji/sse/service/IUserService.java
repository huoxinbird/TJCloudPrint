package cn.edu.tongji.sse.service;

import javax.servlet.http.Cookie;

import cn.edu.tongji.sse.model.User;

public interface IUserService {

	public User userWithCookie(Cookie[] cookies);;
	public boolean register(User u);
		
	/**
	 * @param u User POJO
	 * @return Succeed or not
	 */
	public boolean login(User u);
	
	public boolean isUserLoggedIn(Cookie[] cookies);
}
