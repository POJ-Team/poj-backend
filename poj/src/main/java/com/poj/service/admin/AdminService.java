package com.poj.service.admin;

import com.poj.entity.member.EAuthority;
import com.poj.entity.member.Member;
import com.poj.repository.member.AuthorityRepository;
import com.poj.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {
    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;

    @Transactional
    public void upgradeToUser(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("해당 회원이 존재하지 않습니다.")
        );
        member.getAuthorities().add(authorityRepository.findByEAuthority(EAuthority.ROLE_USER));
    }
}
