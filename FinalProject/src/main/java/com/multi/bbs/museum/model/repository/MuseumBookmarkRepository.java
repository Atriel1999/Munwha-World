package com.multi.bbs.museum.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.bbs.museum.model.vo.MuseumBookmark;

public interface MuseumBookmarkRepository extends JpaRepository<MuseumBookmark, Integer> {

	MuseumBookmark findByBno(int bno);
	List<MuseumBookmark> findByMno(int mno); // 멤버번호로찾기
	
	@Query("select m from MuseumBookmark m where m.msno = :msno and m.mno = :mno")
	MuseumBookmark findByMsnoAndMno(@Param("msno") int msno, @Param("mno") int mno);
	
}
