package com.api.board.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentUpdateRequest {
    private Long docNo;
    private Long replyNo;
    private String comment;
}
