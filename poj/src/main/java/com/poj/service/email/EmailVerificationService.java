package com.poj.service.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailVerificationService {
    private final EmailSendService emailSendService;
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final String TITLE = "POJ 회원가입 인증 코드";

    public void sendCodeToEmail(String toEmail) {
        String code = createCode();
        emailSendService.sendEmail(toEmail, TITLE, code);
    }

    private String createCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int random = secureRandom.nextInt(10);
            code.append(random);
        }
        return code.toString();
    }
}
