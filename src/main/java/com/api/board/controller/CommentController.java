package com.api.board.controller;

import com.api.board.controller.request.CommentDeleteRequest;
import com.api.board.controller.request.CommentRequest;
import com.api.board.controller.request.CommentUpdateRequest;
import com.api.board.controller.response.CommentResponse;
import com.api.board.entity.Comment;
import com.api.board.service.BoardService;
import com.api.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/reply")
@RequiredArgsConstructor
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    CommentService commentService;

    //@GetMapping localhost:8080/board/reply
    //@GetMapping("/list") localhost:8080/board/reply/list
    @GetMapping("/list")
    public ResponseEntity<CommentResponse> list(@RequestParam(name="docNo") Long docNo) throws Exception {
        logger.info("comment docNm : " + docNo);
        return ResponseEntity.ok(new CommentResponse(commentService.getList(docNo)));
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody CommentRequest request){
        logger.info("comment docNm : " + request.getDocNo());
        logger.info("comment writer : " + request.getWriter());
        logger.info("comment comment : " + request.getComment());

        commentService.insert(Comment.translateDTO(request));

        return ResponseEntity.ok("ok");
    }
    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody CommentDeleteRequest request){
        commentService.delete(request);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody CommentUpdateRequest request){
        commentService.update(request);
        return ResponseEntity.ok("ok");
    }
}
