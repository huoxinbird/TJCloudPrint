package cn.edu.tongji.sse.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.tongji.sse.model.Guest;

public class GuestDao extends HibernateDaoSupport implements IGuestDao {

	
	public boolean isValid(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean isExist(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void insertGuest(Guest guest) {
		
		getHibernateTemplate().saveOrUpdate(guest);
	}

	
	public Guest getGuest(String guestId) {
		
		return (Guest) getHibernateTemplate().get(Guest.class,
				new Integer(guestId));
		
		
	}

	
	public void deleteGuest(String guestId) {
		
	}

}
