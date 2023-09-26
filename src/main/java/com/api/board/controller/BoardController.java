package com.api.board.controller;

import com.api.board.controller.request.BoardRequest;
import com.api.board.controller.response.BoardResponse;
import com.api.board.dto.BoardDTO;
import com.api.board.entity.Board;
import com.api.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    BoardService boardService;

    /*
        page : board에서 가져올 페이지
        rows : board에서 가져올 줄 수
        sort : 소트 방법
     */
    @GetMapping("/list")
    public ResponseEntity<BoardResponse> list(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "rows", defaultValue = "5") int rows,
            @RequestParam(name = "sort", defaultValue = "desc") String sort
    ){
        logger.info("load page : " + page);
        logger.info("load rows : " + rows);
        logger.info("sort : " + sort);

        List<BoardDTO> list = boardService.getList(page, rows, sort);

        return ResponseEntity.ok(new BoardResponse(list, boardService.getCount()));
    }
}
