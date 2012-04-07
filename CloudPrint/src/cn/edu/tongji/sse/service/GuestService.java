package cn.edu.tongji.sse.service;

import javax.annotation.Resource;


import org.springframework.stereotype.Component;


import cn.edu.tongji.sse.dao.IGuestDao;
import cn.edu.tongji.sse.model.Guest;

@Component
public class GuestService implements IGuestService {

	private IGuestDao guestDao;
	
	public boolean register(Guest g) {
	
		if (this.guestDao.isExist(g.getName())) {
			return false;
		}
		
		return true;
	}

	
	public boolean login(Guest g) {
		
		
		
		return false;
	}

	@Resource
	public void setGuestDao(IGuestDao guestDao) {
		this.guestDao = guestDao;
	}

	
}
