package cn.edu.tongji.sse.service;

import java.util.List;

import cn.edu.tongji.sse.model.Task;
import cn.edu.tongji.sse.model.User;



public interface ITaskService {
	public Long addTask(String fileName, String fileType, Long shopId, Long userId);
	public List<Task> getTasksOfUser(User u);
	public Task getTask(Long taskId);
}
