package cn.edu.tongji.sse.service;

import java.util.List;

import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.Task;
import cn.edu.tongji.sse.model.User;

public interface IShopService {
	public Shop getShopForUser(User u);
	public boolean addShopForUser(User u, Shop s);
	public void setTokenForShopOfUser(User u, String token);
	public List<Shop> getOpenedShops();
	public List<Task> getTasksForShop(Shop s);
}
