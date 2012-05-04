package cn.edu.tongji.sse.interceptor;

import java.util.Map;

import javax.annotation.Resource;

import cn.edu.tongji.sse.model.User;
import cn.edu.tongji.sse.service.IUserService;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.lang.reflect.*;

public class UserSessionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1871110740691729140L;
	private IUserService userService;

	@Resource
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> map = invocation.getInvocationContext().getSession(); 

		String username = (String)map.get("username");
		String password = (String)map.get("password");
		String userId = (String)map.get("userId");


		boolean isValidUser = false;
		User u = null;
		if (username != null && password != null && userId != null) {
			u = new User();
			u.setUsername(username);
			u.setPassword(password);
			u.setUserId(userId);

			isValidUser = userService.isValid(u);
			

		}
				

		
		Class[] parameterTypes = new Class[1];
		parameterTypes[0] = User.class;
		Object action = invocation.getAction();
		Class actionClass = action.getClass();
		
		Method method = actionClass.getMethod("setSessionUser", parameterTypes);
		if (method != null && isValidUser) {
			method.invoke(action,u);
		}

		
		
		return invocation.invoke(); 
	}

}
