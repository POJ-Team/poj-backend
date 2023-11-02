package com.poj.repository.member;


import com.poj.dto.member.BasicMemberDto;
import com.poj.entity.member.Authority;
import com.poj.entity.member.EAuthority;
import com.poj.entity.member.Member;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MemberRepositoryQuerydsl {
    Optional<Member> findByEmailWithAuthorities(String email);
    Optional<Member> findByIdWithAuthorities(Long id);
    Page<BasicMemberDto> findAllByAuthority(EAuthority authority, int page, int size);
}
