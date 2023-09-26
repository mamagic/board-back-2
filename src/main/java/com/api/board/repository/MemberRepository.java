package com.api.board.repository;

import com.api.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository  extends JpaRepository<Member, Long> {
    public Member findByEmail(final String email);

}
