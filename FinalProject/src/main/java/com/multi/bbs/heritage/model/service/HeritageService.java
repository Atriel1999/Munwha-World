package com.multi.bbs.heritage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.bbs.heritage.model.repository.HeritageRepository;
import com.multi.bbs.heritage.model.vo.Heritage;

@Service
public class HeritageService {
	@Autowired
	private HeritageRepository repo;
	
	public Heritage findByHno(int hno) {
		return repo.findByHno(hno);
	}
}
