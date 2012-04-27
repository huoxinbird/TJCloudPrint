package cn.edu.tongji.sse.service;

import javax.servlet.http.Cookie;

import cn.edu.tongji.sse.model.User;

public interface IUserService {

	public boolean register(User g);
	
	public boolean login(User g);
	
	public boolean isUserLoggedIn(Cookie[] cookies);
}
