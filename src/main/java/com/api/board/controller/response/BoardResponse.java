package com.api.board.controller.response;

import com.api.board.dto.BoardDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BoardResponse {
   private List<BoardDTO> data;
   private int total;
}
