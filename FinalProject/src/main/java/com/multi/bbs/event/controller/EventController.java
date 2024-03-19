package com.multi.bbs.event.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.bbs.event.model.service.EventCalendarService;
import com.multi.bbs.event.model.vo.EventCalendar;

@Controller
public class EventController {
	@Autowired
	private EventCalendarService service;
	
	
	
	@GetMapping("/calendar")
	public String calendar() {
		return "event/eventCalendar";
	}
	
	
	
	// 달력 ajax
	@SuppressWarnings("unchecked")
	@ResponseBody
	@GetMapping(value="/calendarjson", produces = "application/json; charset=utf-8")
	public JSONArray CalendarJson(){
		List<EventCalendar> eList = new ArrayList<EventCalendar>();
		eList = service.getSearchAll();
		JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        
		HashMap<String, Object> hash = new HashMap<String, Object>();
		
		for (int i = 0; i < eList.size(); i++) {
			if (eList.get(i).getSDate().length() != 8 || eList.get(i).getEDate().length() != 8) {
				continue;
			}
			hash.put("title", eList.get(i).getSubTitle());
			hash.put("start", formatDate(eList.get(i).getSDate()));
			hash.put("end", formatDate(eList.get(i).getEDate()));
			if (eList.get(i).getSubPath() != null) {
				hash.put("url", eList.get(i).getSubPath());
			}
			
			jsonObj = new JSONObject(hash);
			jsonArr.add(jsonObj);
		}
		return jsonArr;
	}
	
	// 날짜 포맷 변환용 메소드
		public String formatDate(String input) {
			// 20230101
			// 01234567
			String result = "";
			result += input.substring(0, 4);
			result += "-";
			result += input.substring(4, 6);
			result += "-";
			result += input.substring(6);
			
			return result;
		}
	
}







