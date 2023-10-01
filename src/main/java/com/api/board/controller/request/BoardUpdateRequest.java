package com.api.board.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardUpdateRequest {
    private Long DocNo;
    private String title;
    private String content;
}
