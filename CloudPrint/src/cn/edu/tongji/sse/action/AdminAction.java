package cn.edu.tongji.sse.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class AdminAction implements ServletRequestAware {

	private String code;
	private HttpServletRequest servletRequest;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	
	public String execute() {
		String hostName = this.servletRequest.getHeader("Host");
		System.out.println(hostName);
		return "success";
	}
	
}
