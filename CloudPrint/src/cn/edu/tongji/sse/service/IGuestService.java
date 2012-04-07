package cn.edu.tongji.sse.service;

import cn.edu.tongji.sse.model.Guest;

public interface IGuestService {

	public boolean register(Guest g);
	
	public boolean login(Guest g);
	
}
