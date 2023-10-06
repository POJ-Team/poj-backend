package com.poj.service.token;

import com.poj.repository.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * access token 만 있어도 refresh token 이 필요한 상황이 있다고 가정하여 access token 에 refresh token 을 매핑하였습니다. <br>
 * 하지만 핵심 서비스는 access token 을 key 값으로 access token 의 유효시간만큼 저장하는 것입니다. <br>
 * 이로 인해 access token 이 만료되었는지 확인할 수 있습니다.
 */
@Service
@RequiredArgsConstructor
public class AccessTokenService {
    @Value("${secret.access-token-validity-in-seconds}")
    private Long accessTokenValidityInSeconds;
    private final RedisRepository redisRepository;

    private String getKey(String token) {
        return "access_token:" + token;
    }

    public void mapAtToRt(String accessToken, String refreshToken) {
        redisRepository.setWithTimeout(getKey(accessToken), refreshToken,
                accessTokenValidityInSeconds);
    }

    public String getRt(String accessToken) {
        return redisRepository.get(getKey(accessToken));
    }

    public void deleteAt(String accessToken) {
        redisRepository.delete(getKey(accessToken));
    }

    public boolean isExist(String accessToken) {
        return redisRepository.get(getKey(accessToken)) != null;
    }
}