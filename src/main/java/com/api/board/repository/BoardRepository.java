package com.api.board.repository;

import com.api.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByTitleContaining(String title, Pageable pageable);
    Page<Board> findByWriterContaining(String writer, Pageable pageable);
    Page<Board> findByContentContaining(String content, Pageable pageable);
}
