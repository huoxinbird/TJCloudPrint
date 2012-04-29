package cn.edu.tongji.sse.dao;

import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.User;

public interface IShopDao {
	public boolean addShopForUser(User u, Shop s);
	public Shop getShopForUser(User u);
}
