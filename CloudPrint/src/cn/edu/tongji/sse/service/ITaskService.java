package cn.edu.tongji.sse.service;

import cn.edu.tongji.sse.model.Task;

public interface ITaskService {
	public Long addTask(String fileName, String fileType, Long shopId, Long userId);
	
}
