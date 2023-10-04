package com.api.board.dto;

import com.api.board.controller.request.BoardInsertRequest;
import com.api.board.controller.request.BoardUpdateRequest;
import com.api.board.entity.Board;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor
public class BoardDTO {
    private final Long docNo;
    private final String title;
    private final String writer;
    private final String content;
    private final String regDttm;
    private final int view;
    private final int reply;


    public static BoardDTO translate(Board board){
      return new BoardDTO(board.getId(), board.getTitle(), board.getWriter(), board.getContent(), board.getRegDttm(), board.getView(), board.getComment().size());
    }

    public static BoardDTO translate(BoardInsertRequest request){
        return new BoardDTO(null, request.getTitle(), request.getWriter(), request.getContent(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 0, 0);
    }

    public static BoardDTO translate(BoardUpdateRequest request){
        return new BoardDTO(request.getDocNo(), request.getTitle(), null, request.getContent(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 0, 0);
    }
}
