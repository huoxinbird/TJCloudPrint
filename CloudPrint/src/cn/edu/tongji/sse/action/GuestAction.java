package cn.edu.tongji.sse.action;

import java.util.List;

import javax.annotation.Resource;


import cn.edu.tongji.sse.interceptor.SessionUser;
import cn.edu.tongji.sse.model.Task;
import cn.edu.tongji.sse.model.User;
import cn.edu.tongji.sse.service.ITaskService;

public class GuestAction implements SessionUser {

	private ITaskService taskService;
	private User user;
	private List<Task> guestTasks;
	
	public String home() {
		
		guestTasks = taskService.getTasksOfUser(user);
		
		return "success";
	}

	
	
	
	
	

	@Resource
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}


	
	public void setSessionUser(User user) {
		this.user = user;
		
	}


	public List<Task> getGuestTasks() {
		return guestTasks;
	}





}
