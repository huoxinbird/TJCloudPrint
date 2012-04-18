package cn.edu.tongji.sse.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ModelDriven;

import cn.edu.tongji.sse.model.Guest;
import cn.edu.tongji.sse.service.IGuestService;

public class GuestAction implements ModelDriven<Guest> {
	
	private IGuestService guestService;
	private Guest guest;
			
	
	@Resource
	public void setGuestService(IGuestService guestService) {
		this.guestService = guestService;
	}

	public String login() {
		
		this.guestService.login(this.guest);
		
		return "success";
	}

	public String register() {
		
		System.out.println("username: "+guest.getUsername());
		System.out.println("password: "+guest.getPassword());
		this.guestService.register(this.guest);
		
		
		
		return "success";
	}
	
	
//	public void setGuest(Guest guest) {
//		
//		System.out.println("username: "+guest.getUsername());
//		System.out.println("password:"+guest.getPassword());
//		
//		this.guest = guest;
//	}

	
	public Guest getModel() {
		System.out.println("GuestAction.getModel()");
		
		this.guest = new Guest();
		
		return this.guest;
	}
}
