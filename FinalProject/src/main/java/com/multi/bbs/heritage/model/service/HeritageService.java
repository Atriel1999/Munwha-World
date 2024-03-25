package com.multi.bbs.heritage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.bbs.heritage.model.repository.HReviewRepository;
import com.multi.bbs.heritage.model.repository.HeritageRepository;
import com.multi.bbs.heritage.model.vo.HReview;
import com.multi.bbs.heritage.model.vo.Heritage;


@Service
public class HeritageService {
	@Autowired
	private HReviewRepository reviewrepo;
	
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
		return heritageRepository.findByHno(hno);
	}
	@Transactional(rollbackFor = Exception.class)
	public HReview saveReview(HReview hreview) {
		return reviewrepo.save(hreview);
	}
	public List<HReview> reviewList(Heritage heritage) {
		return reviewrepo.findByHeritage(heritage);
	}
}
