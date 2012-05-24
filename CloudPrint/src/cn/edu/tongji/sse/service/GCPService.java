package cn.edu.tongji.sse.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonNode;
import org.springframework.stereotype.Component;

import cn.edu.tongji.sse.dao.IShopDao;
import cn.edu.tongji.sse.gcp.IGCPUtil;
import cn.edu.tongji.sse.model.Printer;
import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.User;

@Component
public class GCPService implements IGCPService {

	private IShopDao shopDao;
	private IGCPUtil gcpUtil;
	
	public List<Printer> getPrintersOfShop(Shop shop) {
	
		
		JsonNode rootNode = gcpUtil.requestToGcp(shop.getToken(),"http://www.google.com/cloudprint/search");
				
		if (rootNode == null) {
			String new_token = gcpUtil.getRrefreshToken(shop.getRefresh());
			if (new_token == null) {
				return null;
			}
			
			
			shop.setToken(new_token);
			shopDao.updateShop(shop);
			rootNode = gcpUtil.requestToGcp(new_token,"http://www.google.com/cloudprint/search");
		}
		
		if (rootNode != null) {
			return printersFromJson(rootNode);
		}
		
		return null;
	}
	
	private List<Printer> printersFromJson(JsonNode node) {
		
		List<Printer> list = new ArrayList<Printer>();
		
		JsonNode successNode = node.get("success");
		System.out.println("get printer list: "+successNode.asBoolean()); 
		
		JsonNode printersNode = node.get("printers");
		Iterator<JsonNode> printers = printersNode.getElements();
		
		
		while (printers.hasNext()) {
			Printer p = new Printer();
			JsonNode printerNode = (JsonNode) printers.next();
		
			p.setId(printerNode.get("id").asText());
			p.setName(printerNode.get("name").asText());

			
			list.add(p);
			System.out.println("name: "+p.getName());
		}
				
		
		return list;
	}
	
	public void exchangeCodeForTokenForShopOfUser(String code, User u) {
		String[] tokens = gcpUtil.getAccessTokenWithCode(code);
		if (tokens != null) {
			shopDao.setTokenForShopOfUser(u.getId(), tokens[0], tokens[1]);
		}
	}
	
	public void submit(String token, String printerId,  String title, String filePath, String contentType, String tag) {
		gcpUtil.submit(token, printerId, title, filePath, contentType, tag);
	}


	@Resource
	public void setShopDao(IShopDao shopDao) {
		this.shopDao = shopDao;
	}


	

	@Resource
	public void setGcpUtil(IGCPUtil gcpUtil) {
		this.gcpUtil = gcpUtil;
	}

}
