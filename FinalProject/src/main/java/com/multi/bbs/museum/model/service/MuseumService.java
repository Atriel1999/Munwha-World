package com.multi.bbs.museum.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.bbs.heritage.model.vo.HBookmark;
import com.multi.bbs.museum.model.repository.MuseumBookmarkRepository;
import com.multi.bbs.museum.model.repository.MuseumReplyRepository;
import com.multi.bbs.museum.model.repository.MuseumRepository;
import com.multi.bbs.museum.model.vo.Museum;
import com.multi.bbs.museum.model.vo.MuseumBookmark;
import com.multi.bbs.museum.model.vo.MuseumParam;
import com.multi.bbs.museum.model.vo.MuseumReply;
import com.multi.bbs.naverapi.ApiExamSearchNaverAPI;

import jakarta.persistence.criteria.Predicate;




@Service
public class MuseumService {
	@Autowired
	private MuseumRepository museumRepository;
	@Autowired
	private MuseumReplyRepository museumReplyRepo;
	@Autowired
	private MuseumBookmarkRepository bookmarkrepo;
	

	
	@Transactional(rollbackFor = Exception.class)
	public Museum findByNo(int msno) {
		Museum museum = museumRepository.findById(msno).get();
		museum.setReadcount(museum.getReadcount() + 1);
		museumRepository.save(museum);
		return museum;
		
	}
	
	// 리플 관련
	@Transactional(rollbackFor = Exception.class)
	public MuseumReply saveReply(MuseumReply reply) {
		Museum museum = reply.getMuseum();
		museum.setReviewcount(museum.getReviewcount() + 1);
		return museumReplyRepo.save(reply);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void deleteReply(int no) {
		
		Museum museum = museumReplyRepo.findById(no).get().getMuseum();
		if(museum.getReviewcount() > 0) {
			museum.setReviewcount(museum.getReviewcount() - 1);
		}
		museumReplyRepo.deleteById(no);
	}
	
	// 별점 평균 
	//  getAverageRating 정의. 널 아니면 averageRating : 0.0  --> 컨트롤러
	public double getAverageRating(int msno) {
		Double averageRating = museumReplyRepo.findAverageRatingByMuseumMsno(msno);
		return averageRating != null ? averageRating : 0.0; 
	}
	
	
	
	public List<Museum> getSearchAll (String keyword, int page, int limit, String searchAddress){
		Specification<Museum> spec = (root, query, criteriaBuilder) -> {
			 List<Predicate> predicates = new ArrayList<>();
			 // 키워드 공백이 아닐때(검색어입력시에만 작동)
			 if (keyword != null && !keyword.isBlank()) {
	                Predicate namePredicate = criteriaBuilder.like(root.get("msname"), "%" + keyword + "%");
	                predicates.add(namePredicate);
	            }
	          
			 // 주소를 넣었을 때만  (전체 선택시, 널 처럼)
		        if (searchAddress != null && !searchAddress.isBlank() && !searchAddress.equals("전체")) {
		            // 전북특별자치도 
		            if (searchAddress.equals("전북특별자치도")) {
		                Predicate jeonbukPredicate = criteriaBuilder.like(root.get("msaddress"), "%" + "전북특별자치도" + "%");
		                Predicate jeonrabukPredicate = criteriaBuilder.like(root.get("msaddress"), "%" + "전라북도" + "%");
		                predicates.add(criteriaBuilder.or(jeonbukPredicate, jeonrabukPredicate));
		            // 강원특별자치도
		            } else if (searchAddress.equals("강원특별자치도")) {
		                Predicate gangwonSpecialPredicate = criteriaBuilder.like(root.get("msaddress"), "%" + "강원특별자치도" + "%");
		                Predicate gangwonPredicate = criteriaBuilder.like(root.get("msaddress"), "%" + "강원도" + "%");
		                predicates.add(criteriaBuilder.or(gangwonSpecialPredicate, gangwonPredicate));   
		            } else {
		                // 기타 지역
		                Predicate addressPredicate = criteriaBuilder.like(root.get("msaddress"), "%" + searchAddress + "%");
		                predicates.add(addressPredicate);
		            }
		        }
	            
	            
	            
	            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	        };

		    Sort sort = Sort.by("msno").ascending();
			PageRequest request = PageRequest.of(page, limit, sort);
			return museumRepository.findAll(spec, request);
	}
	
	
	
	public int getSearchAllCount(String keyword, String searchAddress) {
	    Specification<Museum> spec = (root, query, criteriaBuilder) -> {
	        List<Predicate> predicates = new ArrayList<>();
	        if (keyword != null && !keyword.isBlank()) {
                Predicate namePredicate = criteriaBuilder.like(root.get("msname"), "%" + keyword + "%");
                predicates.add(namePredicate);
            }
            
	        // 주소를 넣었을 때만  
	        if (searchAddress != null && !searchAddress.isBlank() && !searchAddress.equals("전체")) {
	            // 전북특별자치도 
	            if (searchAddress.equals("전북특별자치도")) {
	                Predicate jeonbukPredicate = criteriaBuilder.like(root.get("msaddress"), "%" + "전북특별자치도" + "%");
	                Predicate jeonrabukPredicate = criteriaBuilder.like(root.get("msaddress"), "%" + "전라북도" + "%");
	                predicates.add(criteriaBuilder.or(jeonbukPredicate, jeonrabukPredicate));
	            // 강원특별자치도
	            } else if (searchAddress.equals("강원특별자치도")) {
	                Predicate gangwonSpecialPredicate = criteriaBuilder.like(root.get("msaddress"), "%" + "강원특별자치도" + "%");
	                Predicate gangwonPredicate = criteriaBuilder.like(root.get("msaddress"), "%" + "강원도" + "%");
	                predicates.add(criteriaBuilder.or(gangwonSpecialPredicate, gangwonPredicate));   
	            } else {
	                // 기타 지역
	                Predicate addressPredicate = criteriaBuilder.like(root.get("msaddress"), "%" + searchAddress + "%");
	                predicates.add(addressPredicate);
	            }
	        }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

	    return museumRepository.count(spec);
	}
	
	
	
	public int getMuseumCount(MuseumParam param) {
		  return getSearchAllCount(param.getsearchName(), param.getsearchAddress());
		}
		
	
	

	public List<Museum> getMuseumList(MuseumParam param){
	
		Sort sort = Sort.by("msno").ascending();
		PageRequest request = PageRequest.of(param.getPage()-1, param.getLimit(), sort);
		return getSearchAll(param.getsearchName(), param.getPage() -1, param.getLimit(), param.getsearchAddress());

    }
		

		 
   // 네이버 api 이미지 
     public String getFirstImageUrlForMuseum(String msname) {
         try {
             // ApiExamSearchNaverAPI 의 searchFirstImageUrl 메소드를 사용하여 이미지 검색
             String firstImageUrl = ApiExamSearchNaverAPI.searchFirstImageUrl(msname);
             return firstImageUrl;
         } catch (IOException | ParseException e) {
             // 예외 처리
             e.printStackTrace();
             return null;
         }
     }
     
     
     
     // 북마크
     @Transactional
 	public MuseumBookmark saveBookmark(MuseumBookmark bookmark) {
 		return bookmarkrepo.save(bookmark);
 	}
 	@Transactional
 	public void deleteBookmark(int bno) {
 		bookmarkrepo.deleteById(bno);
 	}
 	
 	
 	public List<MuseumBookmark> getBookmarkByMember(int mno) {
 		return bookmarkrepo.findByMno(mno);
 	}
 	public MuseumBookmark getBookmarkByMsnoAndMno(int msno, int mno) {
 		return bookmarkrepo.findByMsnoAndMno(msno, mno);
 	}
 	
		
		 
}
			 
			 
		
		
	

