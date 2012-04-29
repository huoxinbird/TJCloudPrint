package cn.edu.tongji.sse.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.User;

public class ShopDao extends HibernateDaoSupport implements IShopDao {

	@Override
	public boolean addShopForUser(User u, Shop s) {
		
		return false;
	}

	@Override
	public Shop getShopForUser(User u) {
		
		return null;
	}

}
