package com.api.board.service;

import com.api.board.dto.BoardDTO;
import com.api.board.entity.Board;
import com.api.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.net.URLDecoder;

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
                .map(BoardDTO::translate)
                .toList();
    }

    /*
        board 페이징 리스트 불러오기
     */
    @Transactional
    public List<BoardDTO> getList(int page, int rows, String sort){
        String sortString = URLDecoder.decode(sort);
        Sort sortBy = Sort.by("");

        System.out.println(sortString);
        System.out.println(sortBy);

        PageRequest pageRequest = PageRequest.of(page - 1, rows, sortBy);
        return boardRepository
                .findAll(pageRequest)
                .stream()
                .map(BoardDTO::translate)
                .toList();
    }

    /*
        board 전체 리스트 갯수 불러오기
     */
    @Transactional
    public int getCount(){
        return getList().size();
    }


    @Transactional
    public BoardDTO getDetail(Long docNo) throws Exception {
        Board board = boardRepository.findById(docNo).orElseThrow(() -> new Exception("존재하지 않는 게시판입니다."));
        board.setView(board.getView() + 1);
        return BoardDTO.translate(board);
    }

    @Transactional
    public Long insert(BoardDTO boardDTO) {
        return boardRepository.save(Board.translate(boardDTO)).getId();
    }

    @Transactional
    public void delete(String docNo) {
        boardRepository.deleteById(Long.parseLong(docNo.substring(9,docNo.length() - 1)));
    }

    @Transactional
    public void update(BoardDTO boardDTO) throws Exception {
        Board board = boardRepository.findById(boardDTO.getDocNo()).orElseThrow(() -> new Exception("존재하지 않는 게시판입니다."));
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
    }
}
