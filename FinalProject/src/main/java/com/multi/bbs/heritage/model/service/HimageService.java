package com.multi.bbs.heritage.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.bbs.heritage.model.repository.HimageRepository;
import com.multi.bbs.heritage.model.vo.Himage;

@Service
public class HimageService {
	@Autowired
	private HimageRepository repo;
	
//	public Himage findByHImageNo(int hImageNo) {
//		return repo.findByHImageNo(hImageNo);
//	}
	
}
