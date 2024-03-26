package com.multi.bbs.heritage.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.bbs.heritage.model.vo.Himage;
import java.util.List;


public interface HimageRepository extends JpaRepository<Himage, Integer>{
	
}
