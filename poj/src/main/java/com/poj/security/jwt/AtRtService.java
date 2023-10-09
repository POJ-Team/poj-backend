package com.poj.security.jwt;

import com.poj.dto.jwt.AtRt;
import com.poj.service.token.AccessTokenService;
import com.poj.service.token.RefreshTokenService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtRtService {
    private final JwtProvider jwtProvider;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;
    private final LocalAuthenticationProvider localAuthenticationProvider;

    public AtRt createAtRt(Authentication authentication) {
        String accessToken = jwtProvider.createAccessToken(authentication);
        String refreshToken = jwtProvider.createRefreshToken(authentication);
        Long accessTokenExpirationInMilliseconds = getTokenExpirationInMilliseconds(accessToken);
        Long refreshTokenExpirationInMilliseconds = getTokenExpirationInMilliseconds(refreshToken);

        accessTokenService.mapAtToRt(accessToken, refreshToken);
        refreshTokenService.mapRtToAt(refreshToken, accessToken);
        return new AtRt(accessToken, refreshToken,
                accessTokenExpirationInMilliseconds, refreshTokenExpirationInMilliseconds);
    }

    public AtRt refresh(String refreshToken) {
        Authentication authentication =
                localAuthenticationProvider.createAuthenticationWithRt(refreshToken);

        String oldAccessToken = refreshTokenService.getAt(refreshToken);
        accessTokenService.deleteAt(oldAccessToken);
        refreshTokenService.deleteRt(refreshToken);

        return createAtRt(authentication);
    }

    private Long getTokenExpirationInMilliseconds(String token) {
        Claims claims = jwtProvider.getClaims(token);
        return claims.getExpiration().getTime();
    }
}
