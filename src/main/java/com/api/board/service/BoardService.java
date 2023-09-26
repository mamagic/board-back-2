package com.api.board.service;

import com.api.board.dto.BoardDTO;
import com.api.board.entity.Board;
import com.api.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    /*
        board 전체 리스트 불러오기
     */
    @Transactional
    public List<BoardDTO> getList(){
        return boardRepository
                .findAll()
                .stream()
                .map(Board::translatedDTO)
                .toList();
    }

    /*
        board 페이징 리스트 불러오기
     */
    @Transactional
    public List<BoardDTO> getList(int page, int rows, String sort){
        PageRequest pageRequest = PageRequest.of(page - 1, rows);
        return boardRepository
                .findAll(pageRequest)
                .stream()
                .map(Board::translatedDTO)
                .toList();
    }

    /*
        board 전체 리스트 갯수 불러오기
     */
    @Transactional
    public int getCount(){
        return getList().size();
    }
}
