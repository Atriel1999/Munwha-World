package com.multi.bbs.event.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.bbs.event.model.vo.EventCalendar;

public interface EventSearchRepository extends JpaRepository<EventCalendar, String> {
	//Paging + sort
	//문화재행사 번호: SEQNO
	Page<EventCalendar> findByOrderBySdateDesc(Pageable pageable);
	
	EventCalendar findBySeqno(String seqno);

	@Query("select e from EVENTCALENDAR e "
			+ " WHERE e.sido = :sido"
			+ " ORDER BY gugun DESC LIMIT 3")
	List<EventCalendar> findBySido(@Param("sido") String sido);

	
	// subtitle검색 + 페이징 + 정렬
    //Page<EventCalendar> findBySubtitleContainingAndSidoEqualsAndGugunEqualsAndSdateGreaterThanEqualAndEdateLessThanEqual(String subtitle, String sido, String gugun, long d1, long d2 ,Pageable pageable);
	
    @Query("select e from EVENTCALENDAR e "
			+ " WHERE e.subtitle like CONCAT('%',:searchValue1,'%')"
			+ " AND e.sido like CONCAT('%',:searchValue2,'%')"
			+ " AND e.gugun like CONCAT('%',:searchValue3,'%')"
			+ " AND ((e.sdate <= CAST(:searchValue4 AS integer) AND e.edate >= CAST(:searchValue4 AS integer)) OR CAST(:searchValue4 AS integer)<100)")
	Page<EventCalendar> findBysearchValueContaining(@Param("searchValue1") String searchValue1,@Param("searchValue2") String searchValue2,
			@Param("searchValue3") String searchValue3,@Param("searchValue4") String searchValue4, Pageable pageable);
    
    
//    @Query("select e from EVENTCALENDAR e "
//			+ " WHERE (coalesce(:searchValue1, null) is null or e.subtitle like CONCAT('%',:searchValue1,'%'))"
//			+ " AND(coalesce(:searchValue2, null) is null or e.sido = :searchValue2)"
//			+ " AND(coalesce(:searchValue3, null) is null or e.gugun = :searchValue3)"
//			+ " AND(coalesce(:searchValue4, null) is null or (e.sdate <= CAST(:searchValue4 AS integer) AND e.edate >= CAST(:searchValue4 AS integer)))"
//			+ " ORDER BY e.sdate DESC")
//	Page<EventCalendar> findBysearchValueContaining(@Param("searchValue1") String searchValue1,@Param("searchValue2") String searchValue2,
//			@Param("searchValue3") String searchValue3,@Param("searchValue4") String searchValue4, Pageable pageable);
    
    // replace함수 -> 자바스크립트로 대체
    //UNSIGNED 대신 Integer사용
    //https://docs.jboss.org/hibernate/stable/core.old/reference/en/html/mapping-types.html
//	@Query("select e from EVENTCALENDAR e "
//			+ " WHERE e.subtitle like CONCAT('%',:searchValue1,'%') "
//			+ " AND e.sido = :searchValue2"
//			+ " AND e.gugun = :searchValue3"
//			+ " AND CAST(e.sdate AS integer) <= CAST(Function('REPLACE',:searchValue4,'-','') AS integer) AND CAST(e.edate AS integer) >= CAST(Function('REPLACE',:searchValue4,'-','') AS integer))"
//			+ " ORDER BY e.sdate DESC")
//	Page<EventCalendar> findBysearchValueContaining(@Param("searchValue1") String searchValue1,@Param("searchValue2") String searchValue2,
//			@Param("searchValue3") String searchValue3,@Param("searchValue4") String searchValue4, Pageable pageable);
//	
//	@Query("select count(*) from EventBoard e"
//			+ " WHERE e.subtitle like :searchValue ")
//	int countBysearchValueContaining(@Param("searchValue") String searchValue);
}
