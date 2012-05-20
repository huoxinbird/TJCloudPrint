package cn.edu.tongji.sse.dao;

import java.util.List;

import cn.edu.tongji.sse.model.Task;

public interface ITaskDao {
	public Long addTask(Task t);
	
	public List<Task> getTasksForShop(Long shopId);
	public List<Task> getTasksOfUser(Long userId);
	public Task getTask(Long taskId);
}
