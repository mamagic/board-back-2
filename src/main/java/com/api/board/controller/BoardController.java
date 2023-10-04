package com.api.board.controller;

import com.api.board.controller.request.BoardInsertRequest;
import com.api.board.controller.request.BoardUpdateRequest;
import com.api.board.controller.response.BoardDetailResponse;
import com.api.board.controller.response.BoardListResponse;
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
    public ResponseEntity<BoardListResponse> list(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "rows", defaultValue = "5") int rows,
            @RequestParam(name = "sort", defaultValue = "desc") String sort
    ){
        logger.info("load page : " + page);
        logger.info("load rows : " + rows);
        logger.info("sort : " + sort);

        List<BoardDTO> list = boardService.getList(page, rows, sort);

        return ResponseEntity.ok(new BoardListResponse(list, boardService.getCount()));
    }
    @GetMapping("/detail")
    public ResponseEntity<BoardDetailResponse> detail(@RequestParam(name = "docNo") Long docNo) throws Exception {
        logger.info("docNo : " + docNo);

        BoardDTO boardDTO = boardService.getDetail(docNo);

        return ResponseEntity.ok(new BoardDetailResponse(boardDTO));
    }

    @PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Long> insert(@RequestBody BoardInsertRequest request){
        logger.info("title : " + request.getTitle());
        logger.info("writer: " + request.getWriter());
        logger.info("content: " + request.getContent());

        return ResponseEntity.ok(boardService.insert(BoardDTO.translate(request)));
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody BoardUpdateRequest request) throws Exception {
        logger.info("title : " + request.getDocNo());
        logger.info("title : " + request.getTitle());
        logger.info("content: " + request.getContent());

        boardService.update(BoardDTO.translate(request));
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody String docNo){
        boardService.delete(docNo);
        return ResponseEntity.ok("ok");
    }
}
