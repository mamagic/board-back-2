package com.api.board.entity;

import com.api.board.controller.request.BoardInsertRequest;
import com.api.board.controller.request.BoardUpdateRequest;
import com.api.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long docNo;

    @Column
    private String title;

    @Column
    private final String writer;

    @Column
    private String content;

    @Column
    private String regDttm;

    @Column
    private int view;

    @Column
    private final int reply;

    @BatchSize(size = 5)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "board")
    private List<Comment> comment = new ArrayList<>();

    public static Board translate(BoardDTO boardDTO){
        return new Board(null, boardDTO.getTitle(), boardDTO.getWriter(), boardDTO.getContent(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 0, 0);
    }
}
