package cn.edu.tongji.sse.action;

import java.io.File;


import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import org.apache.struts2.interceptor.ServletRequestAware;

import cn.edu.tongji.sse.model.User;
import cn.edu.tongji.sse.service.ITaskService;



public class TaskAction implements ServletRequestAware {

	private File doc;
	private String docFileName;
	private String docContentType;
	private ITaskService taskService;
	private HttpServletRequest request;
	private Long shopId;
	private User user;
	private Long taskId;
	
	private boolean isPostMethod() {
		String method = request.getMethod();
		boolean isPostMethod = method.equalsIgnoreCase("POST");
		return isPostMethod;
	}
	
	public String submit() {
		
		if (!isPostMethod()) {
			return "input";
		}
		
		
		Long taskId = taskService.addTask(docFileName, docContentType, shopId, user.getId());
		
		
		System.out.println("filename: "+docFileName);
		System.out.println("filetype: "+docContentType);
		System.out.println("shopid: : "+shopId);
		
		
		ServletContext servletContext = ServletActionContext.getServletContext();		        
		String dataDir = servletContext.getRealPath("/WEB-INF/file");
		File savedFile = new File(dataDir, taskId.toString());
		System.out.println(dataDir);
		
		if (doc == null) {
			System.out.println("doc file null");
		}
		else {
			boolean succeed = doc.renameTo(savedFile);
			if (succeed) {
				System.out.println("rename file succees");
			}
		}
		
	    
		return "success";
	}


	public String finish() {
		
		
		
		
		taskService.finishTask(taskId);
		
		
		return "success";
	}


	public void setDoc(File doc) {
		this.doc = doc;
	}


	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}


	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}



	@Resource
	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public void setSessionUser(User u) {
		this.user = u;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	
	
}
