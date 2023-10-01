package com.api.board.controller;

import com.api.board.controller.request.MemberRequest;
import com.api.board.dto.MemberDTO;
import com.api.board.entity.Member;
import com.api.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
   private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
   @Autowired
   MemberService memberService;

   @PostMapping("/signup")
   public ResponseEntity<String> signIn(@RequestBody MemberRequest request){

      logger.info("userId : " + request.getEmail());
      logger.info("password : " + request.getPassword());

      memberService.register(MemberDTO.translate(request));
      return ResponseEntity.ok("ok");
   }
}
