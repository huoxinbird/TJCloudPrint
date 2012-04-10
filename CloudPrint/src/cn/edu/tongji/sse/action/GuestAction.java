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
		
		this.guestService.register(this.guest);
		
		return "success";
	}
	
	
	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	
	public Guest getModel() {
		
		return new Guest();
	}
}
