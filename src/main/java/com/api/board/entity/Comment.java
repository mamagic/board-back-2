package com.api.board.entity;

import com.api.board.controller.request.CommentRequest;
import com.api.board.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long replyNo;

    @Column
    private final String writer;

    @Column
    private String contents;

    @Column
    private final String regDttm;

    @Column(insertable = false, updatable = false)
    private final Long boardId;

    @ManyToOne // 자기자신에게 column 이 있다
    @JoinColumn(name="boardId")
    private Board board;

    public CommentDTO translatedDTO(){
        return new CommentDTO(replyNo, writer, contents, regDttm, boardId);
    }

    public static CommentDTO translateDTO(CommentRequest request){
        return new CommentDTO(null, request.getWriter(), request.getComment(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), request.getDocNo());
    }


}
