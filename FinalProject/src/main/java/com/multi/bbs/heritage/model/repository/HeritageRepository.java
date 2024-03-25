package com.multi.bbs.heritage.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.bbs.heritage.model.vo.Heritage;


public interface HeritageRepository extends JpaRepository<Heritage, Integer>{

	Heritage findByHno(int hno);

}
