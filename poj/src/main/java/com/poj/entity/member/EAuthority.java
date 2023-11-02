package com.poj.entity.member;

public enum EAuthority {
    ROLE_UNVERIFIED, ROLE_USER, ROLE_ADMIN;

    // ROLE_UNVERIFIED: 1, ROLE_USER: 2, ROLE_ADMIN: 3
    // 이는 member 의 authorities 의 개수와도 같습니다.
    // 예를 들어 ROLE_ADIMIN 의 경우 모든 권한을 보유하므로 authorities 의 개수가 3개입니다.
    public int getLevel() {
        return this.ordinal()+1;
    }
}
