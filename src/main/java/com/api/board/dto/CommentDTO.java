package com.api.board.dto;

import com.api.board.controller.request.CommentRequest;
import com.api.board.entity.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@RequiredArgsConstructor
public class CommentDTO {

    private final Long replyNo;
    private final String writer;
    private final String contents;
    private final String regDttm;
    private final Long boardId;

//    public Comment translateEntity(){
//        return new Comment(null , writer, contents, regDttm, boardId, null);
//    }

    public static CommentDTO translate(Comment comment){
        return new CommentDTO(null,comment.getWriter(), comment.getContents(), comment.getRegDttm(), comment.getBoardId());
    }

    public static CommentDTO translate(CommentRequest request){
        return new CommentDTO(null, request.getWriter(), request.getComment(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), request.getDocNo());
    }
}
