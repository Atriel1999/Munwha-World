package com.multi.bbs.museum.model.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;



import com.multi.bbs.museum.model.vo.Museum;
import java.util.List;


public interface MuseumRepository extends JpaRepository<Museum, Integer>{
	List<Museum> findByMsaddressContaining(String msaddress, Pageable pageable);
	List<Museum> findByMsnameContaining(String msname, Pageable pageable);
	List<Museum> findByMsaddressContainingAndMsnameContaining(String msaddress, String msname, Pageable pageable);
	
	
	List<Museum> findAll(Specification<Museum> spec, Pageable pageable);
	int count(Specification<Museum> spec);
	


}
