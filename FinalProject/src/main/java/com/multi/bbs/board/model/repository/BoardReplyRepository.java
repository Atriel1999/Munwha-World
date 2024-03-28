package com.multi.bbs.board.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.bbs.board.model.vo.BoardReply;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Integer> {


}

