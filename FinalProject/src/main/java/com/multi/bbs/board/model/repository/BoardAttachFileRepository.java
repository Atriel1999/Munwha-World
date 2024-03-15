package com.multi.bbs.board.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.bbs.board.model.vo.BoardAttachFile;

public interface BoardAttachFileRepository extends JpaRepository<BoardAttachFile, Integer> {

}

