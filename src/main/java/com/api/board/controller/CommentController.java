package com.api.board.controller;

import com.api.board.controller.response.CommentResponse;
import com.api.board.service.BoardService;
import com.api.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    CommentService boardService;

    @GetMapping("/reply/list")
    public ResponseEntity<CommentResponse> list(@RequestParam(name="docNo") Long docNo){
        logger.info("comment docNm : " + docNo);
        return ResponseEntity.ok(new CommentResponse(boardService.getList(docNo)));
    }
}
