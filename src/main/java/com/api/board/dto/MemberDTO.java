package com.api.board.dto;

import com.api.board.controller.request.MemberRequest;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MemberDTO {

    private final Long id;
    private final String displayPath;
    private final String email;
    private final String password;
    private final String socialProvider;

    public static MemberDTO translateDTO(MemberRequest request){
        return new MemberDTO(null, null, request.getEmail(), request.getPassword(), null);
    }

}
