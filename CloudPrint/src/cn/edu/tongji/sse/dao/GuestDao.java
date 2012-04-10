package cn.edu.tongji.sse.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.tongji.sse.model.Guest;

public class GuestDao extends HibernateDaoSupport implements IGuestDao {

	
	public boolean isValid(final String username, final String password) {
		List<Guest> list = getHibernateTemplate().execute(new HibernateCallback< List<Guest> > () {
			@SuppressWarnings("unchecked")
			public List<Guest> doInHibernate(Session session)
					throws HibernateException {
				List<Guest> result = session.createCriteria(Guest.class).add(
						Restrictions.eq("username", username)).add(
						Restrictions.eq("password", password)).list();
				return result;
			}
		});
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	
	public boolean isExist(final String username) {
		List<Guest> list = getHibernateTemplate().execute(new HibernateCallback< List<Guest> > () {
			@SuppressWarnings("unchecked")
			public List<Guest> doInHibernate(Session session)
					throws HibernateException {
				List<Guest> result = session.createCriteria(Guest.class).add(
						Restrictions.eq("username", username)).list();
				return result;
			}
		
		});
		if (list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	
	public void insertGuest(Guest guest) {
		
		getHibernateTemplate().saveOrUpdate(guest);
	}

	
	public Guest getGuest(String guestId) {
		
		return getHibernateTemplate().get(Guest.class,
				new Integer(guestId));
		
		
	}

	
	public void deleteGuest(String guestId) {
		
	}

}
