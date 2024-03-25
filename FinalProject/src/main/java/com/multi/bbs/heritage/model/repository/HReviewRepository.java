package com.multi.bbs.heritage.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.bbs.heritage.model.vo.HReview;
import com.multi.bbs.heritage.model.vo.Heritage;

public interface HReviewRepository extends JpaRepository<HReview, Integer>{
	List<HReview> findByHeritage(Heritage heritage);
	void deleteById(int rno);
}
