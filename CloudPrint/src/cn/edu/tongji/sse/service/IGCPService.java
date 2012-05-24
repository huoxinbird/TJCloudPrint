package cn.edu.tongji.sse.service;

import java.util.List;

import cn.edu.tongji.sse.model.Printer;
import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.User;

public interface IGCPService {
	public List<Printer> getPrintersOfShop(Shop shop);
	public void exchangeCodeForTokenForShopOfUser(String code, User u);
	public void submit(String token, String printerId,  String title, String filePath, String contentType, String tag);
}
