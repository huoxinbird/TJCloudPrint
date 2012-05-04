package cn.edu.tongji.sse.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import cn.edu.tongji.sse.model.User;

public class UserDao extends HibernateDaoSupport implements IUserDao {

	
	public User getUser(final String username, final String password) {
		List<User> list = getHibernateTemplate().execute(new HibernateCallback< List<User> > () {
			@SuppressWarnings("unchecked")
			public List<User> doInHibernate(Session session)
					throws HibernateException {
				List<User> result = session.createCriteria(User.class).add(
						Restrictions.eq("username", username)).add(
						Restrictions.eq("password", password)).list();
				
				
				
				
				return result;
			}
		});
		if (list.size() == 1) {
			
			return list.get(0);
		} else {
			return null;
		}
	}

	
	public boolean isExist(final String username) {
		List<User> list = getHibernateTemplate().execute(new HibernateCallback< List<User> > () {
			@SuppressWarnings("unchecked")
			public List<User> doInHibernate(Session session)
					throws HibernateException {
				List<User> result = session.createCriteria(User.class).add(
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

	public boolean isValid(User user) {
		if (user.getId() <= 0) {
			return false;
		}
		
		User u = getHibernateTemplate().get(User.class,new Long(user.getId()));
				
		if (u.getPassword().equals(user.getPassword()) && u.getUsername().equals(user.getUsername())) {
			return true;
		}
		
		return false;
	}
	
	public void insertUser(User user) {
		System.out.println("UserDao.insertUser()");
		
//		Shop s = new Shop();
//		s.setName("setName");
//		User u = new User();
//		u.setUserId("4");
//		s.setUser(u);
//		
//		getHibernateTemplate().saveOrUpdate(s);
		
		getHibernateTemplate().saveOrUpdate(user);
	
	}

	
	public User getUser(String userId) {
		
		return getHibernateTemplate().get(User.class,
				new Integer(userId));
		
		
	}

	
	public void deleteUser(String userId) {
		
	}

}
