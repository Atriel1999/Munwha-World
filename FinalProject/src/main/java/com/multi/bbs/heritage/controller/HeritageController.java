package com.multi.bbs.heritage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.multi.bbs.heritage.model.service.HeritageService;
import com.multi.bbs.heritage.model.service.HimageService;
import com.multi.bbs.heritage.model.vo.HReview;
import com.multi.bbs.heritage.model.vo.Heritage;
import com.multi.bbs.heritage.model.vo.HeritageParam;
import com.multi.bbs.heritage.model.vo.Himage;
import com.multi.bbs.member.model.vo.Member;

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
		Heritage heritage = heritageService.findByHno(hno);
		model.addAttribute("heritage", heritage);
		
		List<HReview> rList = heritageService.reviewList(heritage);
		model.addAttribute("rList", rList);
		
		Optional<Himage> himageop = imgservice.findByHImageNo(hno);
		Himage himage = himageop.get();
		model.addAttribute("himage", himage);
		
		List<Heritage> hList = heritageService.getSearchAll("", "", "국보");
		model.addAttribute("hList", hList);
		
		return "heritage/heritage-detail";
	}
	
	@GetMapping("/heritage-recommend")
	public String hearitageRecommend(Model model) {
		
		return "heritage/heritage-recommend";
	}
	
	@PostMapping("/heritage-detail")
	public String hDetail(Model model,
			@SessionAttribute(name = "loginMember", required = false) Member loginMember,
			 int hno,
			 int rate,
			String rcontent
			) {
		System.out.println("리뷰저장시작");
		HReview hreview = new HReview();
		hreview.setHeritage(heritageService.findByHno(hno));
		hreview.setMno(loginMember.getMno());
		hreview.setMname(loginMember.getName());
		hreview.setRate(rate);
		hreview.setRcontent(rcontent);
		HReview result = heritageService.saveReview(hreview);
		if (result != null) {
			model.addAttribute("msg", "리뷰가 등록되었습니다.");
		} else {
			model.addAttribute("msg", "리뷰 등록에 실패하였습니다.");
		}
		model.addAttribute("location", "/heritage-detail?hno=" + hno);
		return "/common/msg";
	}
	
	@GetMapping("/heritage/replyDel")
	public String deleteReply(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
			int replyNo, int hno) {
		log.info("리플 삭제 요청");
		heritageService.deleteReply(replyNo);

		model.addAttribute("msg", "리플 삭제가 정상적으로 완료되었습니다.");
		model.addAttribute("location", "/heritage-detail?hno=" + hno);
		return "/common/msg";
	}
}

