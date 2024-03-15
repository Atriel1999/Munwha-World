package com.multi.bbs.board.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.bbs.board.model.vo.BoardCategory;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, String> {
	List<BoardCategory> findAllByOrderByOrderno();

}

