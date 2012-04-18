package cn.edu.tongji.sse.service;

import javax.annotation.Resource;


import org.springframework.stereotype.Component;


import cn.edu.tongji.sse.dao.IGuestDao;
import cn.edu.tongji.sse.model.Guest;

@Component
public class GuestService implements IGuestService {

	private IGuestDao guestDao;
	
	public boolean register(Guest g) {
	
		if (this.guestDao.isExist(g.getUsername())) {
			return false;
		}
		
		this.guestDao.insertGuest(g);
		
		
		return true;
	}

	
	public boolean login(Guest g) {
		
		if (this.guestDao.isValid(g.getUsername(), g.getPassword())) {
			System.out.println("valid username and password");
			return true;
		}
		
		return false;
	}

	@Resource
	public void setGuestDao(IGuestDao guestDao) {
		this.guestDao = guestDao;
	}

	
}
