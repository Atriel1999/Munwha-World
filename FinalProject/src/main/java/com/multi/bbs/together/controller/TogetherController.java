package com.multi.bbs.together.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.multi.bbs.common.util.PageInfo;
import com.multi.bbs.member.model.vo.Member;
import com.multi.bbs.museum.model.service.MuseumService;
import com.multi.bbs.museum.model.vo.Museum;
import com.multi.bbs.museum.model.vo.MuseumParam;
import com.multi.bbs.together.model.service.TogetherService;
import com.multi.bbs.together.model.vo.TogetherBoard;
import com.multi.bbs.together.model.vo.TogetherBoardAttachFile;
import com.multi.bbs.together.model.vo.TogetherBoardCategory;
import com.multi.bbs.together.model.vo.TogetherBoardParam;
import com.multi.bbs.together.model.vo.TogetherBoardReply;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j // 
@Controller
public class TogetherController {
	
//	@GetMapping("/together")
//	public String home(Locale locale, Model model, @RequestParam(required = false) String command) {
//		return "weather/weather";
//	}
	
//	@GetMapping("/together")
//	public String togetherView() {
//		log.info("회원 정보 페이지 요청");
//		return "together/together";
//	}
//	
//	@GetMapping("/together-album")
//	public String togetherAlbum() {
//		log.info("회원 정보 페이지 요청");
//		return "together/together-album";
//	}
//	
//	@GetMapping("/togetherBoard")
//	public String togetherBoard() {
//		log.info("회원 정보 페이지 요청");
//		return "together/together-board";
//	}
//	
//	@GetMapping("/togetherBoardDetail")
//	public String togetherBoardDetail() {
//		log.info("회원 정보 페이지 요청");
//		return "together/together-board-detail";
//	}
//	
//	@GetMapping("/togetherBoardWrite")
//	public String togetherBoardWrite() {
//		log.info("회원 정보 페이지 요청");
//		return "together/together-board-write";
//	}
//	@GetMapping("/togetherCalendar")
//	public String togetherCalendar() {
//		log.info("회원 정보 페이지 요청");
//		return "together/together-calendar";
//	}
//	
//	@GetMapping("/togetherChat")
//	public String togetherChat() {
//		log.info("회원 정보 페이지 요청");
//		return "together/together-chat";
//	}

	
	
	@Autowired
	private MuseumService Mservice;
	

	@GetMapping("/togetherSearch")
	public String togetherSearch(Model model, MuseumParam param) {
		log.debug("@@ museum 리스트 요청 param : " + param);
		
		int museumCount = Mservice.getMuseumCount(param);
		PageInfo pageInfo = new PageInfo(param.getPage(), 5, museumCount, 12); // page가 보여질 갯수 : 10, 게시글 목록은 12개
		System.out.println("museumCount : " + museumCount);
		System.out.println("setLimit : " + museumCount);
		System.out.println("setOffset : " + (pageInfo.getStartList() - 1));
		param.setLimit(pageInfo.getListLimit());
		param.setOffset(pageInfo.getStartList() - 1);
		
		List<Museum> list = Mservice.getMuseumList(param);
		
		// 각 박물관에 대해 첫 번째 이미지 URL을 검색하여 설정
	    for (Museum museum : list) {
	        String firstImageUrl = Mservice.getFirstImageUrlForMuseum(museum.getMsname());
	        museum.setImageUrl(firstImageUrl);
	    }
		
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("list", list);
		model.addAttribute("param", param);
		model.addAttribute("museumCount", museumCount);  // 검색총 개수
		return "together/together-search";
	}
	
	@GetMapping("/togetherSearchDetail")
	public String togetherSearchDetail() {
		log.info("회원 정보 페이지 요청");
		return "together/together-search-detail";
	}
	
	@GetMapping("/togetherDetail")
	public String togetherdetail() {
		log.info("회원 정보 페이지 요청");
		return "together/together-detail";
	}
	
	@GetMapping("/togetherDetailSub")
	public String togetherdetailSubb() {
		log.info("회원 정보 페이지 요청");
		return "together/together-detail-sub";
	}
	
	@GetMapping("/togetherAlbum")
	public String togetherPhoto() {
		log.info("회원 정보 페이지 요청");
		return "together/together-album";
	}
	
	@GetMapping("/toboardSche")
	public String togetherSche(Model model, @RequestParam("no") int no) {
//		Museum museum = Mservice.findByNo(no);
//		System.out.println(museum);
//		if (museum == null) {
//			return "redirect:error";
//		}
//		
//		// 별점평균 (doube, model 추가 총 두줄 )
//		double averageRating = Mservice.getAverageRating(no); 
//		
//		//  이미지 (네이버검색)
//        String firstImageUrl = Mservice.getFirstImageUrlForMuseum(museum.getMsname());
//        
//        museum.setImageUrl(firstImageUrl); // Museum 객체에 이미지 URL 설정
//	    
//		model.addAttribute("museum", museum);
//		model.addAttribute("averageRating", String.format("%.2f", averageRating)); // 계산된 평균(소수점두자리) 모델 추가
//		model.addAttribute("replyList", museum.getReplyList());  // 이용후기 
		
		
		return "together/together-board-sche";
	}
	
	
	final static private String savePath = "c:\\together\\";

	@Autowired
	private TogetherService service;
	
	// 카테고리를 공용적으로 사용할때 사용할 Map, List 멤버변수
	// -> 혹시모를 병행처리를 위해 Threadsafe한 클래스로 정리
	private static Vector<TogetherBoardCategory> categoryList;
	private static ConcurrentHashMap<String, String> typeMap; 

	
	// Controller가 실행될때 한번만 초기화하는 메소드
	@PostConstruct	
	public void init() {
		categoryList = service.getBoardCategory();
		typeMap = new ConcurrentHashMap<String, String>();
		for(TogetherBoardCategory item : categoryList) {
			typeMap.put(item.getType(), item.getName());
		}
	}

	
	//채팅방 처리
	@GetMapping("/tochat")
	public String chat() {
		return "together/together-chat";
	}

	//모임 생성 및 삭제
	
	@GetMapping("/together")
	public String tolist(Model model, TogetherBoardParam param) {
//		log.debug("@@ board list 요청 param : " + param);
//		
		int boardCount = service.getBoardCount(param);
		PageInfo pageInfo = new PageInfo(param.getPage(), 10, boardCount, 12); // page가 보여질 갯수 : 10, 게시글 목록은 12개
		System.out.println("boardCount : " + boardCount);
		System.out.println("setLimit : " + boardCount);
		System.out.println("setOffset : " + (pageInfo.getStartList() - 1));
		param.setLimit(pageInfo.getListLimit());
		param.setOffset(pageInfo.getStartList() - 1);
		List<TogetherBoard> list = service.getBoardList(param);
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("list", list);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("typeMap", typeMap);
		model.addAttribute("param", param);
		model.addAttribute("typeList", param.getTypeList());
		
		// 공지사항 분류하는 법
//		if(param.getTypeList() != null && param.getTypeList().size() == 1 && param.getTypeList().get(0).equals("NBOARD")) {
//			return "/board/noticeList";
//		}
		
		//return "board/list";
		return "together/together";
	}
//
//	@GetMapping("/togetherview")
//	public String toview(Model model, @RequestParam("no") int no) {
//		TogetherBoard board = service.findByNo(no);
//		System.out.println(board);
//		if (board == null) {
//			return "redirect:error";
//		}
//
//		model.addAttribute("board", board);
//		model.addAttribute("replyList", board.getReplyList());
//		model.addAttribute("categoryList", categoryList);
//		model.addAttribute("typeMap", typeMap);
//		return "board/view";
//	}
//
//	@GetMapping("/togetherwrite")
//	public String toinviteView(Model model) {
//
//		return "together/write";
//	}
//	
//	@GetMapping("/togetherwrite")
//	public String tpwriteView(Model model) {
//		model.addAttribute("categoryList", categoryList);
//		model.addAttribute("typeMap", typeMap);
//		return "board/write";
//	}
//
//	@PostMapping("/togetherwrite")
//	public String towriteBoard(Model model, HttpSession session,
//			@SessionAttribute(name = "loginMember", required = false) Member loginMember, 
//			@ModelAttribute TogetherBoard board,
//			@RequestParam("type")  String type,
//			@RequestParam(name="upfiles", required = false) List<MultipartFile> upfiles) {
//		log.info("게시글 작성 요청 " + board.toString());
//
//		board.setMember(loginMember);
//		TogetherBoardCategory boardCategory = new TogetherBoardCategory();
//		boardCategory.setType(type);
//		board.setBoardCategory(boardCategory);
//
//		List<TogetherBoardAttachFile> attachFileList = new ArrayList<>();
//		
//		
//		// 파일 저장 로직
//		for(MultipartFile upfile : upfiles) {
//			if(upfile.getSize() == 0) {
//				continue;
//			}
//			String renamedFileName = service.saveFile(upfile, savePath); // 실제 파일 저장로직
//			if(renamedFileName != null) {
//				TogetherBoardAttachFile file = new TogetherBoardAttachFile();
//				file.setBoard(board);
//				file.setRenamedFilename(renamedFileName);
//				file.setOriginalFilename(upfile.getOriginalFilename());
//				attachFileList.add(file);
//			}
//		}
//
//		log.debug("board : " + board);
//		board.setBoardAttachFileList(attachFileList);
//		TogetherBoard result = service.saveBoard(board);
//
//		if (result != null) {
//			model.addAttribute("msg", "게시글이 등록 되었습니다.");
//			model.addAttribute("location", "/board/list");
//		} else {
//			model.addAttribute("msg", "게시글 작성에 실패하였습니다.");
//			model.addAttribute("location", "/board/list");
//		}
//
//		return "common/msg";
//	}
//
//	@PostMapping("/togetherreply")
//	@GetMapping("/togetherreply")
//	public String towriteReply(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
//			@ModelAttribute TogetherBoardReply reply, int bno) {
//		reply.setMember(loginMember);
//		TogetherBoard board = service.findByNo(bno);
//		reply.setBoard(board);
//		log.info("리플 작성 요청 Reply : " + reply);
//
//		TogetherBoardReply result = service.saveReply(reply);
//
//		if (result != null) {
//			model.addAttribute("msg", "리플이 등록되었습니다.");
//		} else {
//			model.addAttribute("msg", "리플 등록에 실패하였습니다.");
//		}
//		model.addAttribute("location", "/board/view?no=" + bno);
//		return "/common/msg";
//	}
//
//	@GetMapping("/togetherdelete")
//	public String todeleteBoard(Model model, HttpSession session,
//			@SessionAttribute(name = "loginMember", required = false) Member loginMember, int boardNo) throws Exception {
//		log.info("게시글 삭제 요청 boardNo : " + boardNo);
//		service.deleteBoard(boardNo, savePath);
//		model.addAttribute("msg", "게시글 삭제가 정상적으로 완료되었습니다.");
//		model.addAttribute("location", "/board/list");
//		return "/common/msg";
//	}
//
//	@GetMapping("/togetherreplyDel")
//	public String todeleteReply(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
//			int replyNo, int boardNo) {
//		log.info("리플 삭제 요청");
//		service.deleteReply(replyNo);
//
//		model.addAttribute("msg", "리플 삭제가 정상적으로 완료되었습니다.");
//		model.addAttribute("location", "/board/view?no=" + boardNo);
//		return "/common/msg";
//	}
//
//	// http://localhost/mvc/board/update?no=27
//	@GetMapping("/togetherupdate")
//	public String toupdateView(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
//			@RequestParam("no") int no) {
//		TogetherBoard board = service.findByNo(no);
//		model.addAttribute("categoryList", categoryList);
//		model.addAttribute("typeMap", typeMap);
//		model.addAttribute("board", board);
//		return "together/together-board-update";
//	}
//
//	@PostMapping("/togetherupdate")
//	public String toupdateBoard(Model model, HttpSession session,
//			@SessionAttribute(name = "loginMember", required = false) Member loginMember, @ModelAttribute TogetherBoard board,
//			@RequestParam(name="upfiles", required = false) List<MultipartFile> upfiles,
//			String type) {
//		log.info("게시글 수정 요청");
//		board.setMember(loginMember);
//		TogetherBoard prevBoard = service.findByNo(board.getBno());
//		
//		List<TogetherBoardAttachFile> attachFileList = new ArrayList<>();
//		// 파일 저장 로직
//		for(MultipartFile upfile : upfiles) {
//			if(upfile.getSize() == 0) {
//				continue;
//			}
//			String renamedFileName = service.saveFile(upfile, savePath); // 실제 파일 저장로직
//			if(renamedFileName != null) {
//				TogetherBoardAttachFile file = new TogetherBoardAttachFile();
//				file.setBoard(board);
//				file.setRenamedFilename(renamedFileName);
//				file.setOriginalFilename(upfile.getOriginalFilename());
//				attachFileList.add(file);
//			}
//		}
//		
//		if(attachFileList.size() != 0) {
//			// 기존 파일 삭제
//			List<TogetherBoardAttachFile> prevAttachFileList = prevBoard.getBoardAttachFileList();
//			for(TogetherBoardAttachFile file : prevAttachFileList) {
//				service.deleteFile(savePath, file);
//				service.deleteAttachFile(file);
//			}
//		}
//		board.setCreateDate(prevBoard.getCreateDate());
//		board.setModifyDate(new Date());
//		board.setBoardAttachFileList(attachFileList);
//		TogetherBoardCategory boardCategory = new TogetherBoardCategory();
//		boardCategory.setType(type);
//		board.setBoardCategory(boardCategory);
//		log.debug("board : " + board);
//		TogetherBoard result = service.saveBoard(board);
//
//		if (result != null) {
//			model.addAttribute("msg", "게시글이 수정 되었습니다.");
//			model.addAttribute("location", "/board/list");
//		} else {
//			model.addAttribute("msg", "게시글 수정에 실패하였습니다.");
//			model.addAttribute("location", "/board/list");
//		}
//
//		return "common/msg";
//	}
//
////  이미지 출력
//	@GetMapping("/togetherfile/{fileName}")
//	@ResponseBody
//	public Resource todownloadImage(@PathVariable("fileName") String fileName, Model model) throws IOException {
//		return new UrlResource("file:" + savePath + fileName);
//	}
//
//	@GetMapping("/togetherfileDown")
//	public ResponseEntity<Resource> tofileDown(
//			@RequestParam("fno") int fno, 
//			@RequestHeader(name = "user-agent") String userAgent) {
//		try {
//			TogetherBoardAttachFile file = service.findBoardAttachFile(fno);
//			System.out.println(file);
//			Resource resource = new UrlResource("file:" + savePath + file.getRenamedFilename() + "");
//			String downName = null;
//
//			// 인터넷 익스플로러 인 경우
//			boolean isMSIE = userAgent.indexOf("MSIE") != -1 || userAgent.indexOf("Trident") != -1;
//
//			if (isMSIE) { // 익스플로러 처리하는 방법
//				downName = URLEncoder.encode(file.getOriginalFilename(), "UTF-8").replaceAll("\\+", "%20");
//			} else {
//				downName = new String(file.getOriginalFilename().getBytes("UTF-8"), "ISO-8859-1"); // 크롬
//			}
//
//			return ResponseEntity.ok()
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + downName + "\"")
//					.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
//					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString()).body(resource);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 실패했을 경우
//	}

	
// ------------------------------------------
// 이아래로 전부 모임 게시판 전용
//	@GetMapping("/board/list") // class 상단의 @RequestMapping로 인하여 /board 생략해야함
	@GetMapping("/toboardlist")
	public String list(Model model, TogetherBoardParam param) {
//		log.debug("@@ board list 요청 param : " + param);
//		
		int boardCount = service.getBoardCount(param);
		PageInfo pageInfo = new PageInfo(param.getPage(), 10, boardCount, 12); // page가 보여질 갯수 : 10, 게시글 목록은 12개
		System.out.println("boardCount : " + boardCount);
		System.out.println("setLimit : " + boardCount);
		System.out.println("setOffset : " + (pageInfo.getStartList() - 1));
		param.setLimit(pageInfo.getListLimit());
		param.setOffset(pageInfo.getStartList() - 1);
		List<TogetherBoard> list = service.getBoardList(param);
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("list", list);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("typeMap", typeMap);
		model.addAttribute("param", param);
		model.addAttribute("typeList", param.getTypeList());
		
		// 공지사항 분류하는 법
//		if(param.getTypeList() != null && param.getTypeList().size() == 1 && param.getTypeList().get(0).equals("NBOARD")) {
//			return "/board/noticeList";
//		}
		
		//return "board/list";
		return "together/together-board";
	}
//
	@GetMapping("/toboardview")
	public String view(Model model, @RequestParam("no") int no) {
		TogetherBoard board = service.findByNo(no);
		System.out.println(board);
		if (board == null) {
			return "redirect:error";
		}

		model.addAttribute("board", board);
		model.addAttribute("replyList", board.getReplyList());
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("typeMap", typeMap);
		return "together/together-board-detail";
	}
	
	@GetMapping("/toboardwrite")
	public String writeView(Model model) {
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("typeMap", typeMap);
		return "together/together-board-write";
	}

	@PostMapping("/toboardwrite")
	public String writeBoard(Model model, HttpSession session,
			@SessionAttribute(name = "loginMember", required = false) Member loginMember, 
			@ModelAttribute TogetherBoard board,
			@RequestParam("type")  String type,
			@RequestParam(name="upfiles", required = false) List<MultipartFile> upfiles) {
		log.info("게시글 작성 요청 " + board.toString());

		board.setMember(loginMember);
		TogetherBoardCategory boardCategory = new TogetherBoardCategory();
		boardCategory.setType(type);
		board.setBoardCategory(boardCategory);

		List<TogetherBoardAttachFile> attachFileList = new ArrayList<>();
		
		
		// 파일 저장 로직
		for(MultipartFile upfile : upfiles) {
			if(upfile.getSize() == 0) {
				continue;
			}
			String renamedFileName = service.saveFile(upfile, savePath); // 실제 파일 저장로직
			if(renamedFileName != null) {
				TogetherBoardAttachFile file = new TogetherBoardAttachFile();
				file.setBoard(board);
				file.setRenamedFilename(renamedFileName);
				file.setOriginalFilename(upfile.getOriginalFilename());
				attachFileList.add(file);
			}
		}

		log.debug("toboardwrite : " + board);
		board.setBoardAttachFileList(attachFileList);
		TogetherBoard result = service.saveBoard(board);

		if (result != null) {
			model.addAttribute("msg", "게시글이 등록 되었습니다.");
			model.addAttribute("location", "/toboardlist");
		} else {
			model.addAttribute("msg", "게시글 작성에 실패하였습니다.");
			model.addAttribute("location", "/toboardlist");
		}

		return "common/msg";
	}

	@PostMapping("/toboardreply")
	@GetMapping("/toboardreply")
	public String writeReply(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
			@ModelAttribute TogetherBoardReply reply, int bno) {
		reply.setMember(loginMember);
		TogetherBoard board = service.findByNo(bno);
		reply.setBoard(board);
		log.info("리플 작성 요청 Reply : " + reply);

		TogetherBoardReply result = service.saveReply(reply);

		if (result != null) {
			model.addAttribute("msg", "리플이 등록되었습니다.");
		} else {
			model.addAttribute("msg", "리플 등록에 실패하였습니다.");
		}
		model.addAttribute("location", "/toboardview?no=" + bno);
		return "/common/msg";
	}

	@GetMapping("/toboarddelete")
	public String deleteBoard(Model model, HttpSession session,
			@SessionAttribute(name = "loginMember", required = false) Member loginMember, int boardNo) throws Exception {
		log.info("게시글 삭제 요청 boardNo : " + boardNo);
		service.deleteBoard(boardNo, savePath);
		model.addAttribute("msg", "게시글 삭제가 정상적으로 완료되었습니다.");
		model.addAttribute("location", "/toboardlist");
		return "/common/msg";
	}

	@GetMapping("/toboardreplyDel")
	public String deleteReply(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
			int replyNo, int boardNo) {
		log.info("리플 삭제 요청");
		service.deleteReply(replyNo);

		model.addAttribute("msg", "리플 삭제가 정상적으로 완료되었습니다.");
		model.addAttribute("location", "/toboardview?no=" + boardNo);
		return "/common/msg";
	}

	// http://localhost/mvc/board/update?no=27
	@GetMapping("/toboardupdate")
	public String updateView(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
			@RequestParam("no") int no) {
		TogetherBoard board = service.findByNo(no);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("typeMap", typeMap);
		model.addAttribute("board", board);
		return "together/together-board-update";
	}

	@PostMapping("/toboradupdate")
	public String updateBoard(Model model, HttpSession session,
			@SessionAttribute(name = "loginMember", required = false) Member loginMember, @ModelAttribute TogetherBoard board,
			@RequestParam(name="upfiles", required = false) List<MultipartFile> upfiles,
			String type) {
		log.info("게시글 수정 요청");
		board.setMember(loginMember);
		TogetherBoard prevBoard = service.findByNo(board.getBno());
		
		List<TogetherBoardAttachFile> attachFileList = new ArrayList<>();
		// 파일 저장 로직
		for(MultipartFile upfile : upfiles) {
			if(upfile.getSize() == 0) {
				continue;
			}
			String renamedFileName = service.saveFile(upfile, savePath); // 실제 파일 저장로직
			if(renamedFileName != null) {
				TogetherBoardAttachFile file = new TogetherBoardAttachFile();
				file.setBoard(board);
				file.setRenamedFilename(renamedFileName);
				file.setOriginalFilename(upfile.getOriginalFilename());
				attachFileList.add(file);
			}
		}
		
		if(attachFileList.size() != 0) {
			// 기존 파일 삭제
			List<TogetherBoardAttachFile> prevAttachFileList = prevBoard.getBoardAttachFileList();
			for(TogetherBoardAttachFile file : prevAttachFileList) {
				service.deleteFile(savePath, file);
				service.deleteAttachFile(file);
			}
		}
		board.setCreateDate(prevBoard.getCreateDate());
		board.setModifyDate(new Date());
		board.setBoardAttachFileList(attachFileList);
		TogetherBoardCategory boardCategory = new TogetherBoardCategory();
		boardCategory.setType(type);
		board.setBoardCategory(boardCategory);
		log.debug("board : " + board);
		TogetherBoard result = service.saveBoard(board);

		if (result != null) {
			model.addAttribute("msg", "게시글이 수정 되었습니다.");
			model.addAttribute("location", "/toboardlist");
		} else {
			model.addAttribute("msg", "게시글 수정에 실패하였습니다.");
			model.addAttribute("location", "/toboardlist");
		}

		return "common/msg";
	}

//  이미지 출력
	@GetMapping("/toboardfile/{fileName}")
	@ResponseBody
	public Resource downloadImage(@PathVariable("fileName") String fileName, Model model) throws IOException {
		return new UrlResource("file:" + savePath + fileName);
	}

	@GetMapping("/toboardfileDown")
	public ResponseEntity<Resource> fileDown(
			@RequestParam("fno") int fno, 
			@RequestHeader(name = "user-agent") String userAgent) {
		try {
			TogetherBoardAttachFile file = service.findBoardAttachFile(fno);
			System.out.println(file);
			Resource resource = new UrlResource("file:" + savePath + file.getRenamedFilename() + "");
			String downName = null;

			// 인터넷 익스플로러 인 경우
			boolean isMSIE = userAgent.indexOf("MSIE") != -1 || userAgent.indexOf("Trident") != -1;

			if (isMSIE) { // 익스플로러 처리하는 방법
				downName = URLEncoder.encode(file.getOriginalFilename(), "UTF-8").replaceAll("\\+", "%20");
			} else {
				downName = new String(file.getOriginalFilename().getBytes("UTF-8"), "ISO-8859-1"); // 크롬
			}

			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + downName + "\"")
					.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
					.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString()).body(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 실패했을 경우
	}

}




















