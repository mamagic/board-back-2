package com.api.board.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardRequest {
    private int page;
    private int rows;
    private String sort;
}
