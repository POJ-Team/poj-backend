package com.poj.repository.member;

import com.poj.dto.member.BasicMemberDto;
import com.poj.entity.member.Authority;
import com.poj.entity.member.EAuthority;
import com.poj.entity.member.Member;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    public Page<BasicMemberDto> findAllByAuthority(EAuthority authority, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createdAt"));
        List<Member> content = query
                .selectFrom(member)
                .leftJoin(member.authorities).fetchJoin()
                .where(authorityFilter(authority))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(member.createdAt.asc())
                .fetch();
        List<BasicMemberDto> dtoContent =
                content.stream().map(BasicMemberDto::new).toList();
        int totalCount = query.selectFrom(member).where(authorityFilter(authority)).fetch().size();

        return new PageImpl<>(dtoContent, pageable, totalCount);
    }

    private BooleanBuilder authorityFilter(EAuthority authority) {
        if (authority == null) {
            return null;
        }
        return new BooleanBuilder(member.authorities.size().eq(authority.getLevel()));
    }
}
