package cn.edu.tongji.sse.action;


import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;




import com.opensymphony.xwork2.ModelDriven;



import cn.edu.tongji.sse.dao.ITaskDao;
import cn.edu.tongji.sse.gcp.Client;
import cn.edu.tongji.sse.gcp.IGCPUtil;
import cn.edu.tongji.sse.interceptor.SessionUser;
import cn.edu.tongji.sse.model.Printer;
import cn.edu.tongji.sse.model.Shop;
import cn.edu.tongji.sse.model.Task;
import cn.edu.tongji.sse.model.User;
import cn.edu.tongji.sse.service.IShopService;
import cn.edu.tongji.sse.service.ITaskService;




public class ShopAction implements SessionUser, ModelDriven<Shop>, ServletRequestAware {
	
	private HttpServletRequest request;

	private User user;
	private Shop shop;

	private IShopService shopService;
	private ITaskService taskService;
	
	private String taskId;
	



	private List<Task> tasks;
	
	//gcp
	private String code;
	private String error;
	private Client client;
	private IGCPUtil gcpUtil;
	private List<Printer> printers;
	private String printerId;

	
	public String home() {		
				
		this.shop = shopService.getShopForUser(user);
		if (shop == null) {
			System.out.println("shop null");
		}
		else {
			this.tasks = shopService.getTasksForShop(shop);
		}
		
		
		
		return "success";
	}
	
	public String open() {
		
		if (shopService.addShopForUser(user, shop))
		{
			return "success";
		}
		
		return "input";
	}
	
	
	public String auth() {

		
		return "success";
	}
	
	public String token() {

						
		if (error != null || code == null) {
			// user deny
			return "error";
		} 
		
		String[] tokens = gcpUtil.getAccessTokenWithCode(code);
		if (tokens != null) {

			shopService.setTokenForShopOfUser(user, tokens[0], tokens[1]);
			
			return "success";
		}
		
		return "error";
	}
	
	

	public String selectprinter() {
		if (taskId == null) {
			return "input";
		}
		else {
			request.getSession().setAttribute("taskId", taskId);
		}
			
		this.shop = shopService.getShopForUser(user);
		if (shop == null) {
			System.out.println("shop null");
		}
		else {
			String tokenString = shop.getToken();
			
			printers = gcpUtil.getPrinterList(tokenString);
            
		}
		return "success";
	}
	
	
	public String printsettings() {
		if (printerId == null) {
			return "input";
		}
		
		taskId = (String)request.getSession().getAttribute("taskId");
		if (taskId == null) {
			return "input";
		}
		
		this.shop = shopService.getShopForUser(user);
		
		Task task = taskService.getTask(Long.parseLong(taskId));
		//gcpUtil.getPrinterDetail(shop.getToken(), printerId);
		ServletContext servletContext = ServletActionContext.getServletContext();		        
		String filePath = servletContext.getRealPath("/WEB-INF/file/"+taskId);
		//File savedFile = new File(dataDir, taskId);
		
		gcpUtil.submit(shop.getToken(), printerId, "first", filePath, "application/msword", task.getFileName());
		
		return "success";
	}
	
	
	
	
	
	
	
	
	public Shop getShop() {
		if (shop == null) {
			System.out.println("get shop null");
		}		
		
		
		return shop;
	}



	
	public void setSessionUser(User user) {
		System.out.println("set username:"+user.getUsername());
		this.user = user;
		
	}
	
	
	public Shop getModel() {
		this.shop = new Shop();
		
		return shop;
	}


	public void setCode(String code) {
		this.code = code;
	}

	public void setError(String error) {
		this.error = error;
	}



	@Resource
	public void setClient(Client client) {		
		this.client = client;
	}
	
	public Client getClient() {
		return this.client;
	}

	

	public List<Task> getTasks() {
		return tasks;
	}

	@Resource
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}
	@Resource
	public void setShopService(IShopService shopService) {
		this.shopService = shopService;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	@Resource
	public void setGcpUtil(IGCPUtil gcpUtil) {
		this.gcpUtil = gcpUtil;
	}

	public List<Printer> getPrinters() {
		return printers;
	}

	public String getPrinterId() {
		return printerId;
	}

	public void setPrinterId(String printerId) {
		this.printerId = printerId;
	}

	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}



	
}
