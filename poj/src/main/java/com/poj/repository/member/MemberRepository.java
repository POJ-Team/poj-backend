package com.poj.repository.member;

import com.poj.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryQuerydsl {
    Boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
