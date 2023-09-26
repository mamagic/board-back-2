package com.api.board.service;

import com.api.board.dto.MemberDTO;
import com.api.board.entity.Member;
import com.api.board.repository.MemberRepository;
import com.api.board.security.RegistrationForm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void register(MemberDTO memberDTO){
        Member member = RegistrationForm.toUser(passwordEncoder, memberDTO);
        memberRepository.save(member);
    }
}
