package com.api.board.service;

import com.api.board.dto.CommentDTO;
import com.api.board.entity.Comment;
import com.api.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    BoardRepository boardRepository;

    @Transactional
    public List<CommentDTO> getList(Long docNo){
        return boardRepository.findById(docNo).get().getComment()
                .stream()
                .map(Comment::translatedDTO)
                .toList();
    }
}
