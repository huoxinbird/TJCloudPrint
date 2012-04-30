package cn.edu.tongji.sse.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.tongji.sse.dao.IShopDao;
import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.User;

@Component
public class ShopService implements IShopService {

	private IShopDao shopDao;
	
	public Shop getShopForUser(User u) {
		
		return shopDao.getShopWithUserId(u.getId());
		
		
	}

	@Resource
	public void setShopDao(IShopDao shopDao) {
		this.shopDao = shopDao;
	}

	
	public boolean addShopForUser(User u, Shop s) {
		
		return shopDao.addShopForUser(u, s);
		
		
	}

	
}
