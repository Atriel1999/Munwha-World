package com.multi.bbs.event.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {
	
	
	
	@GetMapping("/calendar")
	public String calendar() {
		return "event/eventCalendar";
	}
	
	
}







