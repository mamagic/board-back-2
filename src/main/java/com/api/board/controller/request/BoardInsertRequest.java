package com.api.board.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardInsertRequest {
    private String title;
    private String writer;
    private String content;
}
