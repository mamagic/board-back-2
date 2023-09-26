package com.api.board.entity;

import com.api.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long docNo;

    @Column
    private final String title;

    @Column
    private final String writer;

    @Column
    private final LocalDate regDttm;

    @Column
    private final int view;

    @Column
    private final int reply;

    public BoardDTO translatedDTO(){
        return new BoardDTO(docNo, title, writer, regDttm, view, reply);
    }

}
