package cn.edu.tongji.sse.action;

import javax.annotation.Resource;

import cn.edu.tongji.sse.model.Guest;
import cn.edu.tongji.sse.service.IGuestService;

public class GuestAction {
	
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


	public void setGuest(Guest guest) {
		this.guest = guest;
	}
}
