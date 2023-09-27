package com.api.board.entity;

import com.api.board.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long replyNo;

    @Column
    private final String writer;

    @Column
    private final String contents;

    @Column
    private final LocalDate regDttm;

    @Column
    private final Long boardId;

    public CommentDTO translatedDTO(){
        return new CommentDTO(replyNo, writer, contents, regDttm);
    }

}
