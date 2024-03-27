package com.multi.bbs.shop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.multi.bbs.shop.model.service.ShopService;
import com.multi.bbs.shop.model.vo.Shop;

@Controller
public class ShopController {
	@Autowired
	private ShopService service;
	

	@GetMapping("shop/main")
	public String shopmain(Model model) {
		return "shopping/shoppingMain";
	}
	
	@GetMapping("shop/detail")
	public String shopdetail(Model model) {
		return "shopping/shoppingDetail";
	}
	
	@GetMapping("shop/basket")
	public String shopbasket(Model model) {
		return "shopping/shoppingBasket";
	}
	
	
}


