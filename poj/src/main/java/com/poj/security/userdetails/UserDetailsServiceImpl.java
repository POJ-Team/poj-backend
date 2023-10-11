package com.poj.security.userdetails;

import com.poj.entity.member.Member;
import com.poj.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Member member = memberRepository.findByEmailWithAuthorities(email)
                .orElseThrow(() -> new IllegalArgumentException("가입된 이메일이 존재하지 않습니다."));
        return new UserDetailsImpl(member);
    }
}
