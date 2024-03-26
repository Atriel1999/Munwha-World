package com.multi.bbs.museum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.multi.bbs.board.model.vo.Board;
import com.multi.bbs.board.model.vo.BoardReply;
import com.multi.bbs.common.util.PageInfo;
import com.multi.bbs.member.model.vo.Member;
import com.multi.bbs.museum.model.service.MuseumService;
import com.multi.bbs.museum.model.vo.Museum;
import com.multi.bbs.museum.model.vo.MuseumParam;
import com.multi.bbs.museum.model.vo.MuseumReply;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MuseumController {
	
	
	@Autowired
	private MuseumService service;
	
	@PostConstruct
	public void init() {
		
	}
	
	

	@GetMapping("/museumlist")
	public String museumList(Model model, MuseumParam param) {
		log.debug("@@ museum 리스트 요청 param : " + param);
		
		 
		
		int museumCount = service.getMuseumCount(param);
		PageInfo pageInfo = new PageInfo(param.getPage(), 5, museumCount, 12); // page가 보여질 갯수 : 10, 게시글 목록은 12개
		System.out.println("museumCount : " + museumCount);
		System.out.println("setLimit : " + museumCount);
		System.out.println("setOffset : " + (pageInfo.getStartList() - 1));
		param.setLimit(pageInfo.getListLimit());
		param.setOffset(pageInfo.getStartList() - 1);
		
		
		List<Museum> list = service.getMuseumList(param);
		
		// 각 박물관에 대해 첫 번째 이미지 URL을 검색하여 설정
	    for (Museum museum : list) {
	        String firstImageUrl = service.getFirstImageUrlForMuseum(museum.getMsname());
	        museum.setImageUrl(firstImageUrl);
	    }
		
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("list", list);
		model.addAttribute("param", param);
		model.addAttribute("museumCount", museumCount);  // 검색총 개수
		
		return "museum/museumlist";
	}
	
	@GetMapping("/museumview")
	public String museumView(Model model, @RequestParam("no") int no) {
		Museum museum = service.findByNo(no);
		System.out.println(museum);
		if (museum == null) {
			return "redirect:error";
		}
		
		  //  이미지 검색
        String firstImageUrl = service.getFirstImageUrlForMuseum(museum.getMsname());
        museum.setImageUrl(firstImageUrl); // Museum 객체에 이미지 URL 설정
	    
		model.addAttribute("museum", museum);
		model.addAttribute("replyList", museum.getReplyList());
		 
		return "museum/museumview";
	}
	
	
	
	@GetMapping("/error")
	public String error() {
		return "/common/error";
	}
	
	
	@PostMapping("/reply")
	@GetMapping("/reply")
	public String writeReply(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
			@ModelAttribute MuseumReply reply, int msno) {
		reply.setMember(loginMember);
		Museum museum = service.findByNo(msno);
		reply.setMuseum(museum);
		log.info("리플 작성 요청 Reply : " + reply);

		MuseumReply result = service.saveReply(reply);

		if (result != null) {
			model.addAttribute("msg", "리플이 등록되었습니다.");
		} else {
			model.addAttribute("msg", "리플 등록에 실패하였습니다.");
		}
		model.addAttribute("location", "/museumview?no=" + msno);
		return "/common/msg";
	}
	
	@GetMapping("/replyDel")
	public String deleteReply(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
			int replyNo, int museumNo) {
		log.info("리플 삭제 요청");
		service.deleteReply(replyNo);

		model.addAttribute("msg", "리플 삭제가 정상적으로 완료되었습니다.");
		model.addAttribute("location", "/museumview?no=" + museumNo);
		return "/common/msg";
	}
	
	
}
