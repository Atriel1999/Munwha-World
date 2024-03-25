package com.multi.bbs.heritage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.multi.bbs.heritage.model.service.HeritageService;
import com.multi.bbs.heritage.model.service.HimageService;
import com.multi.bbs.heritage.model.vo.Heritage;
import com.multi.bbs.heritage.model.vo.HeritageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 
//@SessionAttributes("loginMember") // loginMember를 Model 취급할때 세션으로 처리하도록 도와주는 어노테이션
@Controller
public class HeritageController {
	@Autowired
	private HeritageService heritageService;
	@Autowired
	private HimageService imgservice;
	
	
	
	@GetMapping("/heritage-search")
	public String searchHearitage(Model model, HeritageParam param) {
		System.out.println("aaaaaaaaaa" + param);
		int size = heritageService.getSearchCount("", "강원", "");
		List<Heritage> list = heritageService.getSearchAll("", "", "국보");
		System.out.println("@@@@ count : " + size);
//		System.out.println(list);
		System.out.println("@@@ size : " + list.size());
		System.out.println("@@@ 0 : " + list.get(0));
		for(Heritage h : list) {
//			System.out.println(h);
		}
		
		
		return "heritage/heritage-search2";
	}
	
	@GetMapping("/heritage-detail")
	public String hearitageDetail(Model model, int hno) {
//		Heritage heritage = service.findByHno(hno);
//		model.addAttribute("heritage", heritage);
//		Himage himage = imgservice.findByHImageNo(hno);
//		model.addAttribute("himage", himage);
		return "heritage/heritage-detail";
	}
	
	@GetMapping("/heritage-recommend")
	public String hearitageRecommend(Model model) {
		
		return "heritage/heritage-recommend";
	}
}

