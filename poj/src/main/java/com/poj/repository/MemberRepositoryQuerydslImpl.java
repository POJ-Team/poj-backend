package com.poj.repository;

import com.poj.entity.member.Member;
import com.poj.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.poj.entity.member.QMember.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberRepositoryQuerydslImpl implements MemberRepositoryQuerydsl{
    private final JPAQueryFactory query;
    @Override
    public Optional<Member> findByEmailWithAuthorities(String email) {
        return Optional.ofNullable(
                query
                        .selectFrom(member)
                        .join(member.authorities)
                        .fetchJoin()
                        .where(member.email.eq(email))
                        .fetchFirst()
        );
    }

    @Override
    public Optional<Member> findByIdWithAuthorities(Long id) {
        return Optional.ofNullable(
                query
                        .selectFrom(member)
                        .join(member.authorities)
                        .fetchJoin()
                        .where(member.id.eq(id))
                        .fetchFirst()
        );
    }
}
