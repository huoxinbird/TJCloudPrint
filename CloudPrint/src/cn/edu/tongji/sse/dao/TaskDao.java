package cn.edu.tongji.sse.dao;

import java.util.Date;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.tongji.sse.model.Task;

public class TaskDao extends HibernateDaoSupport implements ITaskDao {
	public Long addTask(Task t) {
								
		t.setCreateDate(new Date());
		getHibernateTemplate().saveOrUpdate(t);
		
		return t.getId();
	}
	
}
