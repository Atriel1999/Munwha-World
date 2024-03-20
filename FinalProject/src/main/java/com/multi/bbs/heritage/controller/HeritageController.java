package com.multi.bbs.heritage.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.multi.bbs.common.util.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 
//@SessionAttributes("loginMember") // loginMember를 Model 취급할때 세션으로 처리하도록 도와주는 어노테이션
@Controller
public class HeritageController {
	
	@GetMapping("/heritage-search")
	public String searchHearitage(Model model) {
		
		return "heritage/heritage-search2";
	}
	
	@GetMapping("/heritage-detail")
	public String hearitageDetail(Model model) {
		
		return "heritage/heritage-detail";
	}
}
