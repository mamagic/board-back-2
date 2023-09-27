package com.api.board.controller.response;

import com.api.board.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class CommentResponse {
    List<CommentDTO> comments;
}
