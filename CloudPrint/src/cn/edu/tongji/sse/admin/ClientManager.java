package cn.edu.tongji.sse.admin;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class ClientManager {
	static class SingletonHolder {
		static ClientManager instance = new ClientManager();
	}

	public static ClientManager getInstance() {
		return SingletonHolder.instance;
	}

	public ClientManager() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("ClientManager init");
		try {
			this.context = new ClassPathXmlApplicationContext("account.xml");
			this.context = new ClassPathXmlApplicationContext("aa.xml","bb.xml");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("account error");
			//e.printStackTrace();
		}
	}

	private ApplicationContext context;
	
	public Client getClient() {
		System.out.println("getClient");
		
		Client client = (Client)context.getBean("client");
		System.out.println(client.getClientId());
		return client;
	}
	
	
	
	
	
}
