package com.poj.validation.auth;

import com.poj.dto.auth.SignupRequest;
import com.poj.repository.member.MemberRepository;
import com.poj.security.jwt.JwtValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthValidator {
    private final MemberRepository memberRepository;
    private final JwtValidator jwtValidator;
    public void signupValidate(SignupRequest signupRequest) {
        jwtValidator.validateEmailVerificationToken(signupRequest.getToken(), signupRequest.getEmail());
        if (memberRepository.existsByUsername(signupRequest.getUsername())) {
            throw new IllegalArgumentException("이름이 이미 존재합니다.");
        }
        if (memberRepository.existsByEmail(signupRequest.getEmail())) {
            throw new IllegalArgumentException("이메일이 이미 존재합니다.");
        }
    }
}
