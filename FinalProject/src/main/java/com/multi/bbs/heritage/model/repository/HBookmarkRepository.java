package com.multi.bbs.heritage.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.bbs.heritage.model.vo.HBookmark;



public interface HBookmarkRepository extends JpaRepository<HBookmark, Integer>{
	HBookmark findByBno(int bno);
	List<HBookmark> findByMno(int mno); // 멤버번호로찾기
	
	@Query("select h from HBOOKMARK h where h.hno = :hno and h.mno = :mno")
	HBookmark findByHnoAndMno(@Param("hno") int hno, @Param("mno") int mno);
}
