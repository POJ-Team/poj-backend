package com.poj.dto.email;

import lombok.Data;

@Data
public class EmailVerifyResponse {
    boolean success;
    String token;

    public EmailVerifyResponse(boolean success, String token) {
        this.success = success;
        this.token = token;
    }
}
