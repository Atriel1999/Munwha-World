package com.multi.bbs.shop.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.multi.bbs.common.util.PageInfo;
import com.multi.bbs.shop.model.service.ShopService;
import com.multi.bbs.shop.model.vo.Product;
import com.multi.bbs.shop.model.vo.ProductParam;

@Controller
public class ShopController {
	@Autowired
	private ShopService service;
	

	@GetMapping("shop/main")
	public String shopmain(Model model, ProductParam param) {
		System.out.println("쇼핑몰 요청 param : " + param.toString());
		int count = service.getproductcount(param.getSearchValue());
		PageInfo pageInfo = new PageInfo(param.getPage(), 9, count, 9);
		param.setLimit(pageInfo.getListLimit());
		param.setOffset(pageInfo.getStartList() - 1);
		List<Product> list = service.searchProductByTitle(param.getSearchValue(), param.getLimit(), param.getOffset());
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("list", list);
		model.addAttribute("param", param);
		
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


