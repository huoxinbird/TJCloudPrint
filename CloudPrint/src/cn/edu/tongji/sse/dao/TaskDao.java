package cn.edu.tongji.sse.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import cn.edu.tongji.sse.model.Task;

public class TaskDao extends HibernateDaoSupport implements ITaskDao {
	public Long addTask(Task t) {
								
		t.setCreateDate(new Date());
		getHibernateTemplate().saveOrUpdate(t);
		
		return t.getId();
	}
	
	public List<Task> getTasksForShop(final Long shopId) {
		List<Task> list = getHibernateTemplate().execute(new HibernateCallback< List<Task> > () {			
			@SuppressWarnings("unchecked")
			public List<Task> doInHibernate(Session session) throws HibernateException {					
				
				List<Task> result = session.createCriteria(Task.class).add(
						Restrictions.eq("shop.id",shopId)).add(
						Restrictions.eq("state", (short)1)).addOrder(
						Order.asc("createDate")).setFetchSize(5).list();	
								
				return result;
			}
		
		});	
				
		
		return list;
	}
	
	
	public List<Task> getTasksOfUser(final Long userId) {
		List<Task> list = getHibernateTemplate().execute(new HibernateCallback< List<Task> > () {			
			@SuppressWarnings("unchecked")
			public List<Task> doInHibernate(Session session) throws HibernateException {					
				
				List<Task> result = session.createCriteria(Task.class).add(
						Restrictions.eq("user.id",userId)).addOrder(
						Order.desc("createDate")).setFetchSize(5).list();	
								
				return result;
			}
		
		});	
				
		
		return list;
	}
}
