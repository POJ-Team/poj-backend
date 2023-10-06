package com.poj.service.token;

import com.poj.repository.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * refresh token 만 있어도 access token 이 필요한 상황이 있다고 가정하여 refresh token 에 access token 을 매핑하였습니다. <br>
 * 하지만 핵심 서비스는 refresh token 을 key 값으로 refresh token 의 유효시간만큼 저장하는 것입니다. <br>
 * 이로 인해 refresh token 이 만료되었는지 확인할 수 있습니다.
 */
@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    @Value("${secret.refresh-token-validity-in-seconds}")
    private Long refreshTokenValidityInSeconds;
    private final RedisRepository redisRepository;
    private String getKey(String token) {
        return "refresh_token:" + token;
    }
    public void mapRtToAt(String refreshToken, String accessToken) {
        redisRepository.setWithTimeout(getKey(refreshToken), accessToken,
                refreshTokenValidityInSeconds);
    }
    public String getAt(String refreshToken) {
        return redisRepository.get(getKey(refreshToken));
    }
    public void deleteRt(String refreshToken) {
        redisRepository.delete(getKey(refreshToken));
    }

    public boolean isExist(String refreshToken) {
        return redisRepository.get(getKey(refreshToken)) != null;
    }
}
