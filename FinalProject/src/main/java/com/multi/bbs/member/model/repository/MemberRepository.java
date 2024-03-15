package com.multi.bbs.member.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.bbs.member.model.vo.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> { 
	Member findByMemberId(String memberId);
}
