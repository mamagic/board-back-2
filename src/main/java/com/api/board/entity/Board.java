package com.api.board.entity;

import com.api.board.controller.request.BoardInsertRequest;
import com.api.board.controller.request.BoardUpdateRequest;
import com.api.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "board")
//@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor()
public class Board {

    @Id
    @Column(name = "doc_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column
    private String title;

    @Column
    private String writer;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Column
    private String regDttm;

    @Column
    private int view;

    @Column
    private int reply;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "boardId") // comment entity 에 있는 boardId 를 칭한다
    private final List<Comment> comment = new ArrayList<>();

    // board 에 findByid로 board에 id를 검색하면, board entity 가 뽑아져서 나오는데
    // comment 는 아직 나오지 않은 상태로 다른 필드들은 채워진다(LAZY loding 일때만 해당)
    // 그리고 board에 @id로 지정해놓은 값을 comment 테이블에 boardId 로 검색하여 나오는 모든 데이터를 comment에 저장하게하는 쿼리가 나가게 된다

    public static Board translate(BoardDTO boardDTO){
        return new Board(null, boardDTO.getTitle(), boardDTO.getWriter(), boardDTO.getContent(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 0, 0);
    }
}
