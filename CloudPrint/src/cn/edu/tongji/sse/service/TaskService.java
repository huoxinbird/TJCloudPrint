package cn.edu.tongji.sse.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.tongji.sse.dao.ITaskDao;

@Component
public class TaskService implements ITaskService {
	private ITaskDao taskDao;

	public ITaskDao getTaskDao() {
		return taskDao;
	}

	@Resource
	public void setTaskDao(ITaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
	
}
