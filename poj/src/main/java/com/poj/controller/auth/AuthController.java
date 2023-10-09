package com.poj.controller.auth;

import com.poj.dto.auth.SignupRequest;
import com.poj.factory.member.MemberFactory;
import com.poj.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private static final String BEARER_PREFIX = "Bearer ";
    private final MemberFactory memberFactory;
    private final AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<?> registerMember(@Valid @RequestBody SignupRequest signupRequest) {
        memberFactory.createUnverified(signupRequest);
        return ResponseEntity.ok(
                "회원가입이 완료되었습니다."
        );
    }
}
