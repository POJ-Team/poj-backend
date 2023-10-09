package com.poj;

import com.poj.entity.member.Authority;
import com.poj.entity.member.EAuthority;
import com.poj.repository.AuthorityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Init {
    private final InitAuthority initAuthority;

    @PostConstruct
    public void init() {
        initAuthority.init();
    }

    @Component
    @RequiredArgsConstructor
    static class InitAuthority {
        private final AuthorityRepository authorityRepository;

        public void init() {
            authorityRepository.saveAll(List.of(
                    new Authority(EAuthority.ROLE_UNVERIFIED),
                    new Authority(EAuthority.ROLE_USER),
                    new Authority(EAuthority.ROLE_ADMIN)
            ));
        }
    }
}
