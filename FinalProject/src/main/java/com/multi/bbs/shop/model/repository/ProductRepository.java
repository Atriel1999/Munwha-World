package com.multi.bbs.shop.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.bbs.shop.model.vo.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{
	Product findByPno(int pno);
	
	@Query("select p from Product p WHERE p.title like :title ORDER BY p.pno LIMIT :limit OFFSET :offset")
	List<Product> findByTitleContaining(@Param("title") String title, 
			@Param("limit") int limit, @Param("offset") int offset );
	
	@Query("select count(*) from Product p where p.title like :title")
	int countByTitleContaining(@Param("title") String title);
	
	
}
