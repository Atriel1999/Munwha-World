package com.multi.bbs.heritage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.bbs.heritage.model.repository.HeritageRepository;
import com.multi.bbs.heritage.model.vo.Heritage;

@Service
public class HeritageService {
	@Autowired
	private HeritageRepository repo;
	
	@Autowired
	private HeritageRepository heritageRepository;
	
	// String ccbaMnm1, String ccbaCtcdNm, String ccmaName
	public List<Heritage> getSearchAll(String keyword, String region, String category){
		return heritageRepository.findByCcbaMnm1ContainingAndCcbaCtcdNmContainingAndCcmaNameContainingOrderByCcbaMnm1(keyword, region, category);
	}
	public int getSearchCount(String keyword, String region, String category){
		return heritageRepository.countByCcbaMnm1ContainingAndCcbaCtcdNmContainingAndCcmaNameContainingOrderByCcbaMnm1(keyword, region, category);
	}
	
	public Heritage findByHno(int hno) {
		return repo.findByHno(hno);
	}
}
