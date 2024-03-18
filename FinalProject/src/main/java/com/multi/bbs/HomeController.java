package com.multi.bbs;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Locale locale, Model model, @RequestParam(required = false) String command) {
		if(command != null && command.contains("init")) {
			init();
		}
		return "index";
	}
	
	public void init() {}
	
}
