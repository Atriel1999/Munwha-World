package com.multi.bbs.shop.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.bbs.shop.model.vo.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{
	Product findByPno(int pno);
	
	
	List<Product> findByTitleContaining(String Title);
}
