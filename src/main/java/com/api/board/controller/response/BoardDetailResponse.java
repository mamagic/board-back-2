package com.api.board.controller.response;

import com.api.board.dto.BoardDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BoardDetailResponse {
    private Long docNo;
    private String title;
    private String writer;
    private String content;
    private String regDttm;
    private int view;
    private int reply;

    public BoardDetailResponse(BoardDTO boardDTO){
        System.out.println(boardDTO.getRegDttm());
        this.docNo = boardDTO.getDocNo();
        this.title = boardDTO.getTitle();
        this.writer = boardDTO.getWriter();
        this.content = boardDTO.getContent();
        this.regDttm = boardDTO.getRegDttm();
        this.view = boardDTO.getView();
        this.reply = boardDTO.getReply();
    }
}
