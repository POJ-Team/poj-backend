package com.poj.exception.email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailSendException extends RuntimeException{
    public EmailSendException(String toEmail, String title, String context) {
        super(String.format("이메일 전송 중 오류가 발생했습니다. toEmail: %s, title: %s, context: %s", toEmail, title, context));
    }
}
