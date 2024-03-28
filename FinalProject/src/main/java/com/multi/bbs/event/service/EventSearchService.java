package com.multi.bbs.event.service;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.multi.bbs.event.model.repository.EventSearchRepository;
import com.multi.bbs.event.model.vo.EventCalendar;
import com.mysql.cj.log.Log;

import lombok.extern.log4j.Log4j2;

@Service
public class EventSearchService {
	
	@Autowired
	private EventSearchRepository repo;
	
	public EventCalendar findBySeqno(String seqno) {
		return repo.findBySeqno(seqno);
	}
	
	public List<EventCalendar> findBySido(String sido) {
		return repo.findBySido(sido);
	}

	
	public Page<EventCalendar> findAllSortBySdateDesc(Pageable pageable) {
		Sort sort = Sort.by("sdate").descending();
		PageRequest request = PageRequest.of(pageable.getPageNumber(), 10, sort);
		return repo.findByOrderBySdateDesc(request);
	}
	
//	// 0 page start (int page)
//	public Page<EventCalendar> findBySubtitle(String subtitle, String sido, String gugun, long d1, long d2, int page, int pageSize) {
//		Sort sort = Sort.by("sdate").descending();
//		PageRequest request = PageRequest.of(page, pageSize, sort);
//		return repo.findBySubtitleContainingAndSidoEqualsAndGugunEqualsAndSdateGreaterThanEqualAndEdateLessThanEqual(subtitle, sido, gugun, d1, d2, request);
//	}
	
	public Page<EventCalendar> findBysearchValue(String searchValue1, String searchValue2, String searchValue3, String searchValue4, Pageable pageable, String sortBy) {
		
		PageRequest request = null;
		int page = pageable.getPageNumber();
		
		System.out.println("listdbg4: " + sortBy);
		
		if(sortBy==null||sortBy.isEmpty()) {
			sortBy = "sdate";
			request = PageRequest.of(page, 10, Sort.by(sortBy).descending());
		} else if(sortBy.equals("sdatefast")) {
			sortBy = "sdate";
			request = PageRequest.of(page, 10, Sort.by(sortBy).descending());
		} else if(sortBy.equals("sdateold")) {
			sortBy = "sdate";
			request = PageRequest.of(page, 10, Sort.by(sortBy).ascending());
		} else if(sortBy.equals("readcount")) {
			sortBy = "readCount";
			request = PageRequest.of(page, 10, Sort.by(sortBy).descending());
		} 

		
		return repo.findBysearchValueContaining(searchValue1, searchValue2, searchValue3, searchValue4, request);
	}
	
	
	//현재 페이지수 새기
	public long count() {
		return repo.count();
	}
}
