package com.api.board.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class BoardDTO {
    private final Long docNo;
    private final String title;
    private final String writer;
    private final LocalDate regDttm;
    private final int view;
    private final int reply;
}
