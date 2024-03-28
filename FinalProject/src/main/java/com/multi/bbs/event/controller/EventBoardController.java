package com.multi.bbs.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.bbs.common.util.PageInfo;
import com.multi.bbs.event.model.vo.EventCalendar;
import com.multi.bbs.event.service.EventSearchService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/event")
public class EventBoardController {
	@Autowired
	private EventSearchService service;	
		
	@GetMapping("/heritage-event-search")
	public String eventSearchLoading(Model model, Pageable pageable) {
		
		Page<EventCalendar> list = service.findAllSortBySdateDesc(pageable);
		int listcount = (int)list.getTotalElements();
		PageInfo pageInfo = new PageInfo(pageable.getPageNumber(), 5, listcount, 10);
		
		model.addAttribute("list",list);
		log.info("listdbg1 :" + list);
		model.addAttribute("count",service.count());
		model.addAttribute("pageInfo", pageInfo);
		return "/event/heritage-event-search";
	}
	
//	@GetMapping("/eventSearch1")
//	public String eventSearch1(Model model, String subtitle, String sido, String gugun, long d1) {
//		Page<EventCalendar> list = service.findBySubtitle(subtitle, sido, gugun, d1, d1, 0, 10);
//		log.info("listdbg321 :" + list + ", data| 1:" + subtitle + ", 2:" + sido + ", 3:" + gugun + ", 4:" + d1);
//		model.addAttribute("list",list);
//		model.addAttribute("count",list.getTotalElements());
//		return "/event/heritage-event-search";
//	}
	
	@RequestMapping(value = "/eventSearch1", method = RequestMethod.GET)
	public String eventSearch1(Model model, String searchValue1, String searchValue2, String searchValue3, String searchValue4,
			@PageableDefault(page = 0, size = 5) Pageable pageable, @RequestParam(value = "sortBy",defaultValue ="sdate") String sortBy) {
		
		Page<EventCalendar> list = service.findBysearchValue(searchValue1, searchValue2, searchValue3, searchValue4, pageable, sortBy);
		int listcount = (int)list.getTotalElements();
		PageInfo pageInfo = new PageInfo(pageable.getPageNumber(), 5, listcount, 10);
		
		log.info("listdbg3 :" + list + ", data| 1:" + searchValue1 + ", 2:" + searchValue2 + ", 3:" + searchValue3 + ", 4:" + searchValue4 + ", 5:" + sortBy + ", 6:" + pageInfo);
		
		model.addAttribute("list",list);
		model.addAttribute("count",listcount);
		model.addAttribute("sortBy",sortBy);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("searchValue1",searchValue1);
		model.addAttribute("searchValue2",searchValue2);
		model.addAttribute("searchValue3",searchValue3);
		model.addAttribute("searchValue4",searchValue4);

		return "/event/heritage-event-search";
	}
	
	@GetMapping("/heritage-event-single")
	public String heritageEventSingle(Model model, @RequestParam(value = "seqno") String seqno, @RequestParam(value = "sido") String sido) {
		EventCalendar event = service.findBySeqno(seqno);
		
		if (event == null) {
			return "redirect:error";
		}

		List<EventCalendar> eventrecommand = service.findBySido(sido);

		
		if (eventrecommand == null) {
			return "redirect:error";
		}
		
		log.info("listdbg5: " + event.getSubtitle() + ", seqno: " + seqno + ", " + seqno.getClass() + ", eventreco2:" + eventrecommand);
		
		model.addAttribute("event", event);
		model.addAttribute("eventrecommand", eventrecommand);

		
		
		
		return "event/heritage-event-single";
	}

}







