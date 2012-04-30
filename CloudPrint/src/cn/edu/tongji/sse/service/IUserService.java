package cn.edu.tongji.sse.service;



import cn.edu.tongji.sse.model.User;

public interface IUserService {

	
	public boolean register(String username, String password);
		
	/**
	 * @param u User POJO
	 * @return Succeed or not
	 */
	public User login(String username, String password);
	
	public boolean isValid(User u);
}
