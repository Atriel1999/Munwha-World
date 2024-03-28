package com.multi.bbs;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.bbs.shop.model.service.ShopService;
import com.multi.bbs.shop.model.vo.Product;

@Controller
public class HomeController {
	@Autowired
	private ShopService sservice;
	
	@GetMapping("/")
	public String home(Locale locale, Model model, @RequestParam(required = false) String command) {
		if(command != null && command.contains("init")) {
			init();
		}
		List<Product> pList = sservice.searchProductByTitle("", 4, 0);
		model.addAttribute("pList", pList);
		return "home";
	}
	
	@GetMapping("/testhome")
	public String testhome() {
		return "home";
	}
	
	public void init() {}
	
}
