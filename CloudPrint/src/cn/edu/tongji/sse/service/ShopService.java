package cn.edu.tongji.sse.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.tongji.sse.dao.IShopDao;
import cn.edu.tongji.sse.dao.ITaskDao;
import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.Task;
import cn.edu.tongji.sse.model.User;

@Component
public class ShopService implements IShopService {

	private IShopDao shopDao;
	private ITaskDao taskDao;
	
	public void setTokenForShopOfUser(User u, String token) {
		shopDao.setTokenForShopOfUser(u.getId(), token);
	}
	
	public List<Shop> getOpenedShops() {
		
		
		return shopDao.getAuthorizedShops();
	}
	
	public Shop getShopForUser(User u) {
		
		return shopDao.getShopWithUserId(u.getId());
		
		
	}
	
	public List<Task> getTasksForShop(Shop s) {
		return taskDao.getTasksForShop(s.getId());
	}

	@Resource
	public void setShopDao(IShopDao shopDao) {
		this.shopDao = shopDao;
	}
	@Resource
	public void setTaskDao(ITaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
	public boolean addShopForUser(User u, Shop s) {
		
		return shopDao.addShopForUser(u, s);
		
		
	}

	
}
