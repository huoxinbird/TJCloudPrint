package cn.edu.tongji.sse.dao;


import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import cn.edu.tongji.sse.model.User;

public class UserDao extends HibernateDaoSupport implements IUserDao {

	
	public boolean isValid(final String username, final String password) {
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
		if (list.size() > 0) {
			
			return true;
		} else {
			return false;
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

	
	public void insertUser(User user) {

		getHibernateTemplate().saveOrUpdate(user);
		
	}

	
	public User getUser(String userId) {
		
		return getHibernateTemplate().get(User.class,
				new Integer(userId));
		
		
	}

	
	public void deleteUser(String userId) {
		
	}

}
