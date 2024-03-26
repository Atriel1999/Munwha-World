package com.multi.bbs.shop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.bbs.member.model.vo.Member;
import java.util.List;


public interface ShopRepository extends JpaRepository<Member, Integer> { 
}
