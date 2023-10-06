package com.api.board.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardListRequest {
    private String schType;
    private String schVal;
    private int page;
    private int rows;
    private String sort;
}
