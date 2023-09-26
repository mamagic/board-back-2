package com.api.board.security;

import com.api.board.dto.MemberDTO;
import com.api.board.entity.Member;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

  public static Member toUser(PasswordEncoder passwordEncoder, MemberDTO memberDTO) {
    return new Member(null,null,
        memberDTO.getEmail(), passwordEncoder.encode(memberDTO.getPassword()),null);
  }

}
