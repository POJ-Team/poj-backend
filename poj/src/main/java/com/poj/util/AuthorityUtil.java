package com.poj.util;

import com.poj.entity.member.EAuthority;
import com.poj.entity.member.Member;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorityUtil {
    public static EAuthority getAuthority(Member member) {
        if (member.getAuthorities().isEmpty()) {
            throw new IllegalStateException("유저의 권한이 존재하지 않습니다");
        }
        if (member.getAuthorities().stream().anyMatch(authority -> Objects.equals(authority.getName(), EAuthority.ROLE_ADMIN.name()))) {
            return EAuthority.ROLE_ADMIN;
        } else if (member.getAuthorities().stream().anyMatch(authority -> Objects.equals(authority.getName(), EAuthority.ROLE_USER.name()))) {
            return EAuthority.ROLE_USER;
        } else if (member.getAuthorities().stream().anyMatch(authority -> Objects.equals(authority.getName(), EAuthority.ROLE_UNVERIFIED.name()))) {
            return EAuthority.ROLE_UNVERIFIED;
        } else {
            throw new IllegalStateException("적절한 권한이 존재하지 않습니다.");
        }
    }
}
