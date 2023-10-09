package com.poj.dto.auth;

import lombok.Data;

@Data
public class LoginResponse {
    private Long memberId;
    private String username;
    private String email;

    public LoginResponse(LoginSuccess loginSuccess) {
        this.memberId = loginSuccess.getMemberId();
        this.username = loginSuccess.getUsername();
        this.email = loginSuccess.getEmail();
    }
}
