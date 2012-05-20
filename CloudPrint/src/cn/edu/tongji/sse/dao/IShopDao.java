package cn.edu.tongji.sse.dao;

import java.util.List;

import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.User;

public interface IShopDao {
	public boolean addShopForUser(User u, Shop s);
	public Shop getShopWithUserId(Long id);
	public void setTokenForShopOfUser(Long id, String token, String refreshToken);
	public List<Shop> getAuthorizedShops();
	public Shop getShop(Long shopId);
}
