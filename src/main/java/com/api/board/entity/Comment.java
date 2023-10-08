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

//    @Column
//    private final Long boardId;
//
//    @ManyToOne // 자기자신에게 column 이 있다
//    @JoinColumn(name="boardId")
//    private Board board;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private Board board;

    @Column(name = "boardId", insertable = false, updatable = false)
    private Long boardId;

    public Comment(Long replyNo, String writer, String contents, String regDttm) {
        this.replyNo = replyNo;
        this.writer = writer;
        this.contents = contents;
        this.regDttm = regDttm;
    }

    public static Comment translate(CommentDTO commentDTO) {
        return new Comment(null, commentDTO.getWriter(), commentDTO.getContents(), commentDTO.getRegDttm());
    }
}
