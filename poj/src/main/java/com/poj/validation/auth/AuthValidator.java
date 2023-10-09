package com.poj.validation.auth;

import com.poj.dto.auth.SignupRequest;
import com.poj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthValidator {
    private final MemberRepository memberRepository;
    public void signupValidate(SignupRequest signupRequest) {
        if (memberRepository.existsByUsername(signupRequest.getUsername())) {
            throw new IllegalArgumentException("이름이 이미 존재합니다.");
        }
        if (memberRepository.existsByEmail(signupRequest.getEmail())) {
            throw new IllegalArgumentException("이메일이 이미 존재합니다.");
        }
    }
}
