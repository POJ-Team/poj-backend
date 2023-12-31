package com.poj.controller;

import com.poj.dto.email.EmailVerifyResponse;
import com.poj.service.email.EmailVerificationService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 개발 과정에서만 쓰이는 테스트용 controller 입니다. <br>
 * 실제 서비스에서는 사용하지 않습니다.
 */
@RestController
@RequiredArgsConstructor
public class TestController {
    private final EmailVerificationService emailVerificationService;
    @PostMapping("/send-code")
    public ResponseEntity<String> sendCode(@RequestBody TestSendCodeRequest request) {
        emailVerificationService.sendCodeToEmail(request.getEmail());
        return ResponseEntity.ok("인증 코드를 성공적으로 전송하였습니다.");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<EmailVerifyResponse> verifyCode(@RequestBody TestVerifyCodeRequest request) {
        EmailVerifyResponse emailVerifyResponse = emailVerificationService.verifyCode(request.getEmail(), request.getCode());
        // 프론트의 요청에 따라 이메일 검증 절차가 실패 했을 경우 에러 status code 를 내보낼 수도 있습니다.
        return ResponseEntity.ok(emailVerifyResponse);
    }


    @Data
    public static class TestSendCodeRequest {
        private String email;
    }

    @Data
    public static class TestVerifyCodeRequest {
        private String email;
        private String code;
    }
}
