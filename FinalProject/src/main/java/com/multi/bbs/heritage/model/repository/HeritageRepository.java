package com.multi.bbs.heritage.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.bbs.heritage.model.vo.Heritage;


public interface HeritageRepository extends JpaRepository<Heritage, Integer>{

	List<Heritage> findByCcbaMnm1ContainingAndCcbaCtcdNmContainingAndCcmaNameContainingAndCcceNameContainingOrderByCcbaMnm1(String ccbaMnm1, String ccbaCtcdNm, String ccmaName, String ccceName, Pageable pageable);
	int countByCcbaMnm1ContainingAndCcbaCtcdNmContainingAndCcmaNameContainingAndCcceNameContainingOrderByCcbaMnm1(String ccbaMnm1, String ccbaCtcdNm, String ccmaName, String ccceName);
	
	Heritage findByHno(int hno);

}
