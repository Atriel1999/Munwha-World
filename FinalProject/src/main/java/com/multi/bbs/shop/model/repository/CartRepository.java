package com.multi.bbs.shop.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.bbs.shop.model.vo.Cart;


public interface CartRepository extends JpaRepository<CartRepository, Integer>  {
	Cart findByBno(int bno);
	List<Cart> findByMno(int mno); // 멤버번호로찾기
	
	@Query("SELECT c FROM Cart c " +
	           "INNER JOIN Product p ON c.pNo = p.pNo " +
	           "INNER JOIN Member m ON c.mNo = m.mNo " +
	           "WHERE m.mNo = :mNo")
	    List<Cart> findByMemberId(@Param("mNo") Long mNo);
}
