package com.poj.dto.jwt;

import lombok.Data;

@Data
public class AtRt {
    String accessToken;
    String refreshToken;
    Long accessTokenExpirationInMilliseconds;
    Long refreshTokenExpirationInMilliseconds;

    public AtRt(String accessToken, String refreshToken,
                Long accessTokenExpirationInMilliseconds, Long refreshTokenExpirationInMilliseconds) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpirationInMilliseconds = accessTokenExpirationInMilliseconds;
        this.refreshTokenExpirationInMilliseconds = refreshTokenExpirationInMilliseconds;
    }
}
