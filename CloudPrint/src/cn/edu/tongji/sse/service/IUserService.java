package cn.edu.tongji.sse.service;



import cn.edu.tongji.sse.model.User;

public interface IUserService {

	
	public boolean register(User u);
		
	/**
	 * @param u User POJO
	 * @return Succeed or not
	 */
	public boolean login(User u);
	
	public boolean isValid(User u);
}
