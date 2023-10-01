package com.api.board.service;

import com.api.board.controller.request.CommentDeleteRequest;
import com.api.board.controller.request.CommentUpdateRequest;
import com.api.board.dto.CommentDTO;
import com.api.board.entity.Board;
import com.api.board.entity.Comment;
import com.api.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    BoardRepository boardRepository;

    @Transactional
    public List<CommentDTO> getList(Long docNo) throws Exception {
        // get()으로 하면 boardRepository.findById(docNo)가 null이라면 exception이 발생한다.
        // orElseThrow 는boardRepository.findById(docNo) 이 null 일때 실행된다.
        return boardRepository.findById(docNo).orElseThrow(() -> new Exception("존재하지 않는 게시판입니다."))
                .getComment()
                .stream()
                .map(Comment::translatedDTO)
                .toList();
        // get()으로 하면 boardRepository.findById(docNo)가 null이라면 exception이 발생한다.
//        return boardRepository.findById(docNo).get().getComment()
//                .stream()
//                .map(Comment::translatedDTO)
//                .toList();

        // ifPresent()으로 하면 boardRepository.findById(docNo)가 null이여도 exception이 발생 안한다.
        // ifPresent(e -> {}) ifPresent는 데이터가 null이 아닐때만 실행이 된다.
        // ifPresent(e -> {})는 반환값이 void이다.
        // isPresent()는 boardRepository.findById(docNo)의 Optional<Board> 데이터에 Board가 null인지 아닌지 유무를 판단하는 메소드이다.
        // isPresent()는 true, false인지만 반환해준다.
//        Optional<Board> boardOptional = boardRepository.findById(docNo);
//        if(boardOptional.isPresent()) {
//            return boardOptional.get().getComment()
//                    .stream()
//                    .map(Comment::translatedDTO)
//                    .toList();
//        }
//        throw new Exception("존재하지 않는 게시판입니다.");
    }

    @Transactional
    public void insert(CommentDTO commentDTO) {
        Board board = boardRepository.findById(commentDTO.getBoardId()).get();
        board.getComment().add(commentDTO.translateEntity());
    }

    @Transactional
    public void delete(CommentDeleteRequest request) {
        Board board = boardRepository.findById(request.getDocNo()).get();
        board.getComment().removeIf(comment -> comment.getReplyNo().equals(request.getReplyNo()));
    }

    @Transactional
    public void update(CommentUpdateRequest request) {
        Board board = boardRepository.findById(request.getDocNo()).get();
        board.getComment().stream()
                          .filter(reply -> reply.getReplyNo().equals(request.getReplyNo()))
                          .findFirst()
                          .get()
                          .setContents(request.getComment());
    }
}
