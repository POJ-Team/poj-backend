package com.poj.controller.auth;

import com.poj.dto.auth.LoginRequest;
import com.poj.dto.auth.LoginResponse;
import com.poj.dto.auth.LoginSuccess;
import com.poj.dto.auth.SignupRequest;
import com.poj.factory.member.MemberFactory;
import com.poj.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
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

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        LoginSuccess loginSuccess = authService.login(request);

        String accessToken = loginSuccess.getAtRt().getAccessToken();
        String refreshToken = loginSuccess.getAtRt().getRefreshToken();
        Long refreshTokenExpirationInMilliseconds =
                loginSuccess.getAtRt().getRefreshTokenExpirationInMilliseconds();

        Long refreshTokenExpirationFromNowInSeconds =
                (refreshTokenExpirationInMilliseconds - System.currentTimeMillis()) / 1000;

        HttpCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .path("/")
                .maxAge(refreshTokenExpirationFromNowInSeconds)
//                .secure(true)
                .httpOnly(true)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + accessToken)
                .body(new LoginResponse(loginSuccess));
    }
}
