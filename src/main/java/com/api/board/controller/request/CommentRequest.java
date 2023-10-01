package com.api.board.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRequest {
    private Long docNo;
    private String writer;
    private String comment;
}
