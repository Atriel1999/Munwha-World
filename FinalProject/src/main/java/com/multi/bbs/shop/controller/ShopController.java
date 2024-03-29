package com.multi.bbs.shop.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.multi.bbs.board.model.vo.Board;
import com.multi.bbs.common.util.PageInfo;
import com.multi.bbs.member.model.vo.Member;
import com.multi.bbs.shop.model.service.ShopService;
import com.multi.bbs.shop.model.vo.Product;
import com.multi.bbs.shop.model.vo.ProductParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShopController {
	
	
	@Autowired
	private ShopService service;
	

	@GetMapping("shop/main")
	public String shopmain(Model model, ProductParam param, String order) {
		int count = service.getproductcount(param.getSearchValue());
		PageInfo pageInfo = new PageInfo(param.getPage(), 5, count, 9);
		param.setLimit(pageInfo.getListLimit());
		param.setOffset(pageInfo.getStartList() - 1);
		System.out.println("쇼핑몰 요청 param : " + param.toString());
		if (order == null) {
			order = "asc";
		}
		if (order.equals("asc")) {
			List<Product> list = service.searchProductByTitle(param.getSearchValue(), param.getLimit(), param.getOffset());
			model.addAttribute("list", list);
		} else if (order.equals("desc")) {
			List<Product> list = service.searchProductByTitleDesc(param.getSearchValue(), param.getLimit(), param.getOffset());
			model.addAttribute("list", list);
		}
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("param", param);
		model.addAttribute("count", count);
		model.addAttribute("order", order);
		
		return "shopping/shoppingMain";
	}
	
	@GetMapping("shop/detail")
	public String shopdetail(Model model) {
		
		return "shopping/shoppingDetail";
	}
	

}


