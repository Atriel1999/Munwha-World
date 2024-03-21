package com.multi.bbs.together.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.multi.bbs.kakao.KakaoService;
import com.multi.bbs.member.model.service.MemberService;
import com.multi.bbs.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;


@Slf4j // 
@Controller
public class TogetherController {
	
//	@GetMapping("/together")
//	public String home(Locale locale, Model model, @RequestParam(required = false) String command) {
//		return "weather/weather";
//	}
	
	@GetMapping("/together")
	public String togetherView() {
		log.info("회원 정보 페이지 요청");
		return "together/together";
	}
	
	@GetMapping("/togetherBoard")
	public String togetherBoard() {
		log.info("회원 정보 페이지 요청");
		return "together/together-board";
	}
	
	@GetMapping("/togetherBoardDetail")
	public String togetherBoardDetail() {
		log.info("회원 정보 페이지 요청");
		return "together/together-board-detail";
	}
	
	@GetMapping("/togetherBoardWrite")
	public String togetherBoardWrite() {
		log.info("회원 정보 페이지 요청");
		return "together/together-board-write";
	}
	@GetMapping("/togetherCalendar")
	public String togetherCalendar() {
		log.info("회원 정보 페이지 요청");
		return "together/together-calendar";
	}
	
	@GetMapping("/togetherChat")
	public String togetherChat() {
		log.info("회원 정보 페이지 요청");
		return "together/together-chat";
	}
	@GetMapping("/togetherDetail")
	public String togetherdetail() {
		log.info("회원 정보 페이지 요청");
		return "together/together-detail";
	}
	@GetMapping("/togetherSearch")
	public String togetherSearch() {
		log.info("회원 정보 페이지 요청");
		return "together/together-search";
	}
	
	@GetMapping("/togetherSearchDetail")
	public String togetherSearchDetail() {
		log.info("회원 정보 페이지 요청");
		return "together/together-search-detail";
	}
	
	@GetMapping("/togetherPhoto")
	public String togetherPhoto() {
		log.info("회원 정보 페이지 요청");
		return "together/together-photh";
	}
	
	
//	
//	@GetMapping("/logout")
//	public String logout(SessionStatus status) { // status : 세션의 상태 확인과 해제가 가능한 클래스
//		log.info("status : " + status.isComplete());
//		status.setComplete();
//		log.info("status : " + status.isComplete());
//		return "redirect:/";
//	}
//	
//
//	// AJAX 회원아이디 중복 검사부
//	@GetMapping("/member/idCheck")
//	public ResponseEntity<Map<String, Object>> idCheck(String id){
//		log.info("아이디 중복 확인 : " + id);
//		
//		boolean result = service.validate(id);
//		Map<String,	Object> map = new HashMap<String, Object>();
//		map.put("validate", result);
//		
//		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
//	}
//	
//	
//	@PostMapping("/member/update")
//	public String update(Model model, 
//			@ModelAttribute Member updateMember, // request에서 온 값
//			@SessionAttribute(name = "loginMember", required = false) Member loginMember // 세션 값
//			) {
//		log.info("update 요청, updateMember : " + updateMember);
//		if(loginMember == null) {
//			model.addAttribute("msg","잘못된 접근입니다.");
//			model.addAttribute("location","/");
//			return "common/msg";
//		}
//		
//		updateMember.setMno(loginMember.getMno());
//		updateMember.setPassword(loginMember.getPassword());
//		Member result = service.save(updateMember);
//		
//		if(result != null) {
//			model.addAttribute("loginMember", service.findById(loginMember.getMemberId())); // DB에서 있는 값을 다시 세션에 넣어주는 코드
//			model.addAttribute("msg", "회원정보를 수정하였습니다.");
//			model.addAttribute("location", "/member/view");
//		}else {
//			model.addAttribute("msg", "회원정보 수정에 실패하였습니다.");
//			model.addAttribute("location", "/member/view");
//		}
//		return "common/msg";
//	}
//	
//	
//	@GetMapping("/member/view")
//	public String memberView() {
//		log.info("회원 정보 페이지 요청");
//		return "member/view";
//	}
//	
//	@GetMapping("/member/updatePwd")
//	public String updatePwdPage() {
//		return "/member/updatePwd";
//	}
//	
//	@PostMapping("/member/updatePwd")
//	public String updatePwd(Model model,
//			@SessionAttribute(name = "loginMember", required = false) Member loginMember,
//			String userPwd
//			) {
//		Member result = service.updatePwd(loginMember, userPwd);
//		
//		if(result != null) {
//			model.addAttribute("msg", "비밀번호 수정에 성공하였습니다.");
//		}else {
//			model.addAttribute("msg", "비밀번호 변경에 실패했습니다.");
//		}
//		model.addAttribute("script", "self.close()");
//		return "/common/msg";
//	}
//	
//	@GetMapping("/member/delete")
//	public String delete(Model model,
//			@SessionAttribute(name = "loginMember", required = false) Member loginMember) {
//		service.delete(loginMember.getMno());
//		model.addAttribute("msg", "회원탈퇴에 성공하였습니다.");
//		model.addAttribute("location","/logout");
//		return  "/common/msg";
//	}
//	
//	
//	
//	
//	
//	
//	
//	// ------------------------------------------------------------
//	// 로그인페이지 진입
//	@GetMapping("/login")
//	public String loginpage() {
//		log.debug("로그인페이지");
//		return "member/signin-light";
//	}
//	
//	
//	
//	
//	
//	// 회원가입페이지 진입
//	@GetMapping("/signup")
//	public String signuppage() {
//		log.debug("회원가입페이지");
//		return "member/signup-light";
//	}
//	
//	// 회원가입 처리
//	@PostMapping("/signup")
//	public String enroll(Model model, Member member) {
//		log.debug("회원가입 요청 : " + member.toString());
//		Member result = service.save(member);
//		
//		if(result != null) { // 성공
//			model.addAttribute("msg", "회원가입에 성공하였습니다.");
//			model.addAttribute("location", "/");
//		}else { // 실패
//			model.addAttribute("msg", "회원가입에 실패하였습니다. 입력정보를 확인하세요.");
//			model.addAttribute("location", "/");
//		}
//		return "common/msg";
//	}
//	
//	// 카카오회원가입요청
//	@GetMapping("/signupkakao")
//	public String signupkakao(Model model, String code) {
//		log.debug("카카오회원가입요청");
//		if(code != null) {
//			try {
//				String url = "http://localhost/signupkakao";
//				log.debug("code: " + code);
//				String token = kakaoService.getToken(code, url);
//				Map<String, Object> map = kakaoService.getUserInfo(token);
//				model.addAttribute("kakaoMap", map);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		return "member/signup-light";
//	}
//	
//	// 카카오로그인
//	@GetMapping("/kakaoLogin")
//	public String kakaoLogin(Model model, String code) {
//		if(code != null) {
//			try {
//				String url = "http://localhost/kakaoLogin";
//				String token = kakaoService.getToken(code, url);
//				Map<String, Object> map = kakaoService.getUserInfo(token);
//				String kakaoToken = (String) map.get("id");
//				Member loginMember = service.loginKakao(kakaoToken);
//				if (loginMember != null) {
//					model.addAttribute("loginMember", loginMember);
//					return "redirect:/";
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		model.addAttribute("msg", "로그인 실패");
//		model.addAttribute("location", "/");
//		return "common/msg";
//	}
//	
//	
//	
//	
//	// 마이페이지 회원설정
//	@GetMapping("/mypageProfile")
//	public String myprofile(
//			Model model,
//			@SessionAttribute(name = "loginMember", required = false) Member loginMember
//			) {
//		if(loginMember == null) {
//			model.addAttribute("msg","잘못된 접근입니다.");
//			model.addAttribute("location","/");
//			return "common/msg";
//		}
//		model.addAttribute("loginMember", loginMember);
//		return "member/mypageProfile";
//	}
//	
//	// 마이페이지 내가쓴글
//	@GetMapping("/mypageReviews")
//	public String myreviews(
//			Model model,
//			@SessionAttribute(name = "loginMember", required = false) Member loginMember
//			) {
//		if(loginMember == null) {
//			model.addAttribute("msg","잘못된 접근입니다.");
//			model.addAttribute("location","/");
//			return "common/msg";
//		}
//		model.addAttribute("loginMember", loginMember);
//		return "member/mypageReviews";
//	}
//	
//	// 마이페이지 즐겨찾기
//	@GetMapping("/mypageBookmarks")
//	public String mybookmarks(
//			Model model,
//			@SessionAttribute(name = "loginMember", required = false) Member loginMember
//			) {
//		if(loginMember == null) {
//			model.addAttribute("msg","잘못된 접근입니다.");
//			model.addAttribute("location","/");
//			return "common/msg";
//		}
//		model.addAttribute("loginMember", loginMember);
//		return "member/mypageBookmarks";
//	}
//	
//	// 마이페이지 주문목록
//	@GetMapping("/mypageShopList")
//	public String myshoplist(
//			Model model,
//			@SessionAttribute(name = "loginMember", required = false) Member loginMember
//			) {
//		if(loginMember == null) {
//			model.addAttribute("msg","잘못된 접근입니다.");
//			model.addAttribute("location","/");
//			return "common/msg";
//		}
//		model.addAttribute("loginMember", loginMember);
//		return "member/mypageShopList";
//	}	
//	
}




















