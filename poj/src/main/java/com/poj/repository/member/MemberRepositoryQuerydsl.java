package com.poj.repository.member;


import com.poj.entity.member.Member;

import java.util.Optional;

public interface MemberRepositoryQuerydsl {
    Optional<Member> findByEmailWithAuthorities(String email);
    Optional<Member> findByIdWithAuthorities(Long id);
}
