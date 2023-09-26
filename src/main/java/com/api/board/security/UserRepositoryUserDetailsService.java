package com.api.board.security;

import com.api.board.entity.Member;
import com.api.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService 
        implements UserDetailsService {

  private MemberRepository memberRepo;

  @Autowired
  public UserRepositoryUserDetailsService(MemberRepository userRepo) {
    this.memberRepo = userRepo;
  }
  
  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    Member member = memberRepo.findByEmail(username);
    if (member != null) {
      System.out.println("-------------------------------");
      System.out.println("username: " + member.getEmail());
      System.out.println("password: " + member.getPassword());
      return member;
    }
    throw new UsernameNotFoundException(
                    "User '" + username + "' not found");
  }

}
