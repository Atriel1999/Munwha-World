package com.multi.bbs.museum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.multi.bbs.common.util.PageInfo;
import com.multi.bbs.heritage.model.vo.HBookmark;
import com.multi.bbs.member.model.service.MemberService;
import com.multi.bbs.member.model.vo.Member;
import com.multi.bbs.museum.model.service.MuseumService;
import com.multi.bbs.museum.model.vo.Museum;
import com.multi.bbs.museum.model.vo.MuseumBookmark;
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
		
		// 별점평균 (doube, model 추가 총 두줄 )
		double averageRating = service.getAverageRating(no); 
		
		//  이미지 (네이버검색)
        String firstImageUrl = service.getFirstImageUrlForMuseum(museum.getMsname());
        
        museum.setImageUrl(firstImageUrl); // Museum 객체에 이미지 URL 설정
	    
		model.addAttribute("museum", museum);
		model.addAttribute("averageRating", String.format("%.2f", averageRating)); // 계산된 평균(소수점두자리) 모델 추가
		model.addAttribute("replyList", museum.getReplyList());  // 이용후기 
		
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
			model.addAttribute("msg", "이용후기가 등록되었습니다.");
		} else {
			model.addAttribute("msg", "이용후기 등록이 실패하였습니다.");
		}
		model.addAttribute("location", "/museumview?no=" + msno);
		return "/common/msg";
	}
	
	
	@GetMapping("/replyDel")
	public String deleteReply(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
			int replyNo, int museumNo) {
		log.info("리플 삭제 요청");
		service.deleteReply(replyNo);

		model.addAttribute("msg", "이용후기 삭제가 정상적으로 완료되었습니다.");
		model.addAttribute("location", "/museumview?no=" + museumNo);
		return "/common/msg";
	}
	
	
	// 북마크  --> 박물관 상세페이지에서 북마크 추가, 삭제 가능 
	@GetMapping("/Bookmark")
	public String toggleBookmark(Model model, int mno, int msno) {
        MuseumBookmark mbookmark = new MuseumBookmark(0, service.findByNo(msno),msno,mno);
        
     // 북마크 저장
     		if (service.findBookmarkByMsnoAndMno(msno, mno) == null) {
     			System.out.println("북마크 저장요청");
     			service.addBookmark(mbookmark);
     			model.addAttribute("msg", "즐겨찾기 저장");
     			model.addAttribute("location",  "/museumview?no=" + msno);
     		} else if (service.findBookmarkByMsnoAndMno(msno, mno) != null) {
     			// 북마크 삭제
     			int bno = service.findBookmarkByMsnoAndMno(msno, mno).getBno();
     			System.out.println("북마크 삭제요청");
     			service.deleteBookmark(bno);
     			model.addAttribute("msg", "즐겨찾기 삭제");
     			model.addAttribute("location",  "/museumview?no=" + msno);
     		}
        return "/common/msg"; 
    }
	
	
	
  
	
}
