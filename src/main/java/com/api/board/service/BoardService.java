package com.api.board.service;

import com.api.board.dto.BoardDTO;
import com.api.board.entity.Board;
import com.api.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public List<BoardDTO> getList(String schType, String schVal,int page, int rows, String sort){
        String sortString = URLDecoder.decode(sort);

        Pattern pattern = Pattern.compile("(\\w+)\\s+(asc|desc)");

        Sort sortBy = null;

        // 정규 표현식을 사용하여 문자열 파싱
        Matcher matcher = pattern.matcher(sortString);
        while (matcher.find()) {
            String property = matcher.group(1); // 정렬 기준 필드 이름
            String direction = matcher.group(2); // 정렬 방향

            Sort.Order order = direction.equals("asc") ? Sort.Order.asc(property) : Sort.Order.desc(property);

            if (sortBy == null) {
                sortBy = Sort.by(order);
            } else {
                sortBy.and(order);
            }
        }

        Pageable pageable = PageRequest.of(page - 1, rows, sortBy);

        Page<Board> pageResult = null;
        if(schType.equals("title")){
            pageResult = boardRepository.findByTitleContaining(schVal, pageable);
        }else if(schType.equals("writer")){
            pageResult = boardRepository.findByWriterContaining(schVal, pageable);
        }else if(schType.equals("content")){
            pageResult = boardRepository.findByContentContaining(schVal, pageable);
        }
        else{
            pageResult = boardRepository.findAll(pageable);
        }

        List<Board> result = pageResult != null ? pageResult.getContent() : null;

        return result.stream()
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
        return boardRepository.save(Board.translate(boardDTO)).getDocNo();
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
