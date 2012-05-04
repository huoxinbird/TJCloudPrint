package cn.edu.tongji.sse.action;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import cn.edu.tongji.sse.service.ITaskService;



public class TaskAction {

	private File docFile;
	private String docFileName;
	private String docContentType;
	private ITaskService taskService;
	
	public String submit() {
		
		ServletContext servletContext = ServletActionContext.getServletContext();		        
		String dataDir = servletContext.getRealPath("/WEB-INF/file");
		File savedFile = new File(dataDir, docFileName);
		docFile.renameTo(savedFile);
	    
		return "success";
	}




	public void setDocFile(File docFile) {
		this.docFile = docFile;
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
	
	
	
}
