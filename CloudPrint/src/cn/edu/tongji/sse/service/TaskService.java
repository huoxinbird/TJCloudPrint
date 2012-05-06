package cn.edu.tongji.sse.service;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Component;

import cn.edu.tongji.sse.dao.ITaskDao;
import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.Task;
import cn.edu.tongji.sse.model.User;

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
	
	private Short mimeToShort(String mimetype) {
		
		if (mimetype.equalsIgnoreCase("application/msword")) {
			return new Short((short)1);
		}
		
		return null;
	}
	
	public Long addTask(String fileName, String fileType, Long shopId, Long userId) {
		Task t = new Task();
		t.setFileName(fileName);
		
		Short type = mimeToShort(fileType);
		if (type == null) {
			return null;
		}
		
		t.setFileType(type);
		
		Shop shop = new Shop();
		shop.setId(shopId);
		t.setShop(shop);
		
		User user = new User();
		user.setId(userId);
		t.setUser(user);
		
		t.setState((short)1);
		
		return taskDao.addTask(t);
	}
	
	public List<Task> getTasksOfUser(User u) {
		return taskDao.getTasksOfUser(u.getId());
	}
}
