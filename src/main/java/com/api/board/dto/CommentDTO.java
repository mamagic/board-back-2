package com.api.board.dto;

import com.api.board.entity.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class CommentDTO {

    private final Long replyNo;
    private final String writer;
    private final String contents;
    private final String regDttm;
    private final Long boardId;

    public Comment translateEntity(){
        return new Comment(null , writer, contents, regDttm, boardId, null);
    }

}
