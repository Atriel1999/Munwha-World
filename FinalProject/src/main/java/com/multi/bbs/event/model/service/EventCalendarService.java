package com.multi.bbs.event.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.bbs.event.model.repository.EventCalendarRepository;
import com.multi.bbs.event.model.vo.EventCalendar;

@Service
public class EventCalendarService {
	@Autowired
	private EventCalendarRepository repo;
	
	public List<EventCalendar> getSearchAll(){
		return repo.findAll();
	}
	
	
}
