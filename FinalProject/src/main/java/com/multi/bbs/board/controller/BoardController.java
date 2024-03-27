package com.multi.bbs.board.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.multi.bbs.board.model.service.BoardService;
import com.multi.bbs.board.model.vo.Board;
import com.multi.bbs.board.model.vo.BoardAttachFile;
import com.multi.bbs.board.model.vo.BoardCategory;
import com.multi.bbs.board.model.vo.BoardParam;
import com.multi.bbs.board.model.vo.BoardReply;
import com.multi.bbs.common.util.PageInfo;
import com.multi.bbs.member.model.vo.Member;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board") // 요청 url의 상위 url을 모두 처리할때 사용
@Controller
public class BoardController {

	final static private String savePath = "c:\\bbs\\";

	@Autowired
	private BoardService service;
	
	// 카테고리를 공용적으로 사용할때 사용할 Map, List 멤버변수
	// -> 혹시모를 병행처리를 위해 Threadsafe한 클래스로 정리
	private static Vector<BoardCategory> categoryList;
	private static ConcurrentHashMap<String, String> typeMap; 

	
	// Controller가 실행될때 한번만 초기화하는 메소드
	@PostConstruct	
	public void init() {
		categoryList = service.getBoardCategory();
		typeMap = new ConcurrentHashMap<String, String>();
		for(BoardCategory item : categoryList) {
			typeMap.put(item.getType(), item.getName());
		}
	}
	

//	@GetMapping("/board/list") // class 상단의 @RequestMapping로 인하여 /board 생략해야함
	@GetMapping("/list")
	public String list(Model model, BoardParam param) {
		log.debug("@@ board list 요청 param : " + param);
		
		int boardCount = service.getBoardCount(param);
		PageInfo pageInfo = new PageInfo(param.getPage(), 10, boardCount, 12); // page가 보여질 갯수 : 10, 게시글 목록은 12개
		System.out.println("boardCount : " + boardCount);
		System.out.println("setLimit : " + boardCount);
		System.out.println("setOffset : " + (pageInfo.getStartList() - 1));
		param.setLimit(pageInfo.getListLimit());
		param.setOffset(pageInfo.getStartList() - 1);
		List<Board> list = service.getBoardList(param);
		
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("list", list);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("typeMap", typeMap);
		model.addAttribute("param", param);
		model.addAttribute("typeList", param.getTypeList());
		
		// 공지사항 분류하는 법
		if(param.getTypeList() != null && param.getTypeList().size() == 1 && param.getTypeList().get(0).equals("NOTICE")) {
			return "/board/notice";
		}
		
		// 질문답변 분류하는 법
		if (param.getTypeList() != null && param.getTypeList().size() == 1 && param.getTypeList().get(0).equals("QUESTION")) {
			return "/board/q_a";
		}
		
		// 자유게시판 분류하는 법
		if (param.getTypeList() != null && param.getTypeList().size() == 1 && param.getTypeList().get(0).equals("PLAIN")) {
			return "/board/freeBoard";
		}

		
		return "board/list";
	}

	@GetMapping("/view")
	public String view(Model model, @RequestParam("no") int no) {
		Board board = service.findByNo(no);
		System.out.println(board);
		if (board == null) {
			return "redirect:error";
		}

		model.addAttribute("board", board);
		model.addAttribute("replyList", board.getReplyList());
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("typeMap", typeMap);
		return "board/view";
	}

	@GetMapping("/error")
	public String error() {
		return "/common/error";
	}

	@GetMapping("/write")
	public String writeView(Model model) {
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("typeMap", typeMap);
		return "board/write";
	}

	@PostMapping("/write")
	public String writeBoard(Model model, HttpSession session,
			@SessionAttribute(name = "loginMember", required = false) Member loginMember, 
			@ModelAttribute Board board,
			@RequestParam("type")  String type,
			@RequestParam(name="upfiles", required = false) List<MultipartFile> upfiles) {
		log.info("게시글 작성 요청 " + board.toString());

		board.setMember(loginMember);
		BoardCategory boardCategory = new BoardCategory();
		boardCategory.setType(type);
		board.setBoardCategory(boardCategory);

		List<BoardAttachFile> attachFileList = new ArrayList<>();
		
		
		// 파일 저장 로직
		for(MultipartFile upfile : upfiles) {
			if(upfile.getSize() == 0) {
				continue;
			}
			String renamedFileName = service.saveFile(upfile, savePath); // 실제 파일 저장로직
			if(renamedFileName != null) {
				BoardAttachFile file = new BoardAttachFile();
				file.setBoard(board);
				file.setRenamedFilename(renamedFileName);
				file.setOriginalFilename(upfile.getOriginalFilename());
				attachFileList.add(file);
			}
		}

		log.debug("board : " + board);
		board.setBoardAttachFileList(attachFileList);
		Board result = service.saveBoard(board);

		if (result != null) {
			model.addAttribute("msg", "게시글이 등록 되었습니다.");
			model.addAttribute("location", "/board/list");
		} else {
			model.addAttribute("msg", "게시글 작성에 실패하였습니다.");
			model.addAttribute("location", "/board/list");
		}

		return "common/msg";
	}

	@PostMapping("/reply")
	@GetMapping("/reply")
	public String writeReply(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
			@ModelAttribute BoardReply reply, int bno) {
		reply.setMember(loginMember);
		Board board = service.findByNo(bno);
		reply.setBoard(board);
		log.info("리플 작성 요청 Reply : " + reply);

		BoardReply result = service.saveReply(reply);

		if (result != null) {
			model.addAttribute("msg", "리플이 등록되었습니다.");
		} else {
			model.addAttribute("msg", "리플 등록에 실패하였습니다.");
		}
		model.addAttribute("location", "/board/view?no=" + bno);
		return "/common/msg";
	}

	@GetMapping("/delete")
	public String deleteBoard(Model model, HttpSession session,
			@SessionAttribute(name = "loginMember", required = false) Member loginMember, int boardNo) throws Exception {
		log.info("게시글 삭제 요청 boardNo : " + boardNo);
		service.deleteBoard(boardNo, savePath);
		model.addAttribute("msg", "게시글 삭제가 정상적으로 완료되었습니다.");
		model.addAttribute("location", "/board/list");
		return "/common/msg";
	}

	@GetMapping("/replyDel")
	public String deleteReply(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
			int replyNo, int boardNo) {
		log.info("리플 삭제 요청");
		service.deleteReply(replyNo);

		model.addAttribute("msg", "리플 삭제가 정상적으로 완료되었습니다.");
		model.addAttribute("location", "/board/view?no=" + boardNo);
		return "/common/msg";
	}

	// http://localhost/mvc/board/update?no=27
	@GetMapping("/update")
	public String updateView(Model model, @SessionAttribute(name = "loginMember", required = false) Member loginMember,
			@RequestParam("no") int no) {
		Board board = service.findByNo(no);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("typeMap", typeMap);
		model.addAttribute("board", board);
		return "board/update";
	}

	@PostMapping("/update")
	public String updateBoard(Model model, HttpSession session,
			@SessionAttribute(name = "loginMember", required = false) Member loginMember, @ModelAttribute Board board,
			@RequestParam(name="upfiles", required = false) List<MultipartFile> upfiles,
			String type) {
		log.info("게시글 수정 요청");
		board.setMember(loginMember);
		Board prevBoard = service.findByNo(board.getBno());
		
		List<BoardAttachFile> attachFileList = new ArrayList<>();
		// 파일 저장 로직
		for(MultipartFile upfile : upfiles) {
			if(upfile.getSize() == 0) {
				continue;
			}
			String renamedFileName = service.saveFile(upfile, savePath); // 실제 파일 저장로직
			if(renamedFileName != null) {
				BoardAttachFile file = new BoardAttachFile();
				file.setBoard(board);
				file.setRenamedFilename(renamedFileName);
				file.setOriginalFilename(upfile.getOriginalFilename());
				attachFileList.add(file);
			}
		}
		
		if(attachFileList.size() != 0) {
			// 기존 파일 삭제
			List<BoardAttachFile> prevAttachFileList = prevBoard.getBoardAttachFileList();
			for(BoardAttachFile file : prevAttachFileList) {
				service.deleteFile(savePath, file);
				service.deleteAttachFile(file);
			}
		}
		board.setCreateDate(prevBoard.getCreateDate());
		board.setModifyDate(new Date());
		board.setBoardAttachFileList(attachFileList);
		BoardCategory boardCategory = new BoardCategory();
		boardCategory.setType(type);
		board.setBoardCategory(boardCategory);
		log.debug("board : " + board);
		Board result = service.saveBoard(board);

		if (result != null) {
			model.addAttribute("msg", "게시글이 수정 되었습니다.");
			model.addAttribute("location", "/board/list");
		} else {
			model.addAttribute("msg", "게시글 수정에 실패하였습니다.");
			model.addAttribute("location", "/board/list");
		}

		return "common/msg";
	}

//  이미지 출력
	@GetMapping("/file/{fileName}")
	@ResponseBody
	public Resource downloadImage(@PathVariable("fileName") String fileName, Model model) throws IOException {
		return new UrlResource("file:" + savePath + fileName);
	}

	@GetMapping("/fileDown")
	public ResponseEntity<Resource> fileDown(
			@RequestParam("fno") int fno, 
			@RequestHeader(name = "user-agent") String userAgent) {
		try {
			BoardAttachFile file = service.findBoardAttachFile(fno);
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
