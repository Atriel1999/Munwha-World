package com.multi.bbs.museum.model.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 
import org.springframework.data.repository.query.Param;

import com.multi.bbs.museum.model.vo.MuseumReply;

public interface MuseumReplyRepository extends JpaRepository<MuseumReply, Integer> {
	
	// 별점 평균 
	// MuseumReply 엔티티 중 msno. 특정:msno값과 일치하는 rating 값의 평균 구하기 (쿼리작성) -> 서비스 
	// 메소드이름도 중요 
	@Query("SELECT AVG(r.rating) FROM MuseumReply r WHERE r.museum.msno = :msno")
	Double findAverageRatingByMuseumMsno(@Param("msno") Integer msno);
	
	
}
