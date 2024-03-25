package com.multi.bbs.heritage.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.bbs.heritage.model.vo.Heritage;


public interface HeritageRepository extends JpaRepository<Heritage, Integer>{

	List<Heritage> findByCcbaMnm1ContainingAndCcbaCtcdNmContainingAndCcmaNameContainingOrderByCcbaMnm1(String ccbaMnm1, String ccbaCtcdNm, String ccmaName);
	int countByCcbaMnm1ContainingAndCcbaCtcdNmContainingAndCcmaNameContainingOrderByCcbaMnm1(String ccbaMnm1, String ccbaCtcdNm, String ccmaName);
	
	Heritage findByHno(int hno);

}
