package com.poj.factory.member;

import com.poj.dto.auth.SignupRequest;
import com.poj.entity.member.EAuthority;
import com.poj.entity.member.Member;
import com.poj.repository.member.AuthorityRepository;
import com.poj.repository.member.MemberRepository;
import com.poj.validation.auth.AuthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class MemberFactory {
    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthValidator authValidator;

    public Member createUnverified(SignupRequest request) {
        authValidator.signupValidate(request);
        Member member = Member.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(Set.of(
                        authorityRepository.findByEAuthority(EAuthority.ROLE_UNVERIFIED)))
                .build();
        memberRepository.save(member);
        return member;

    }

    public Member createUser(SignupRequest request) {
        authValidator.signupValidate(request);
        Member member = Member.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(Set.of(
                        authorityRepository.findByEAuthority(EAuthority.ROLE_UNVERIFIED),
                        authorityRepository.findByEAuthority(EAuthority.ROLE_USER)))
                .build();
        memberRepository.save(member);
        return member;
    }

    public Member createAdmin(SignupRequest request) {
        authValidator.signupValidate(request);
        Member member = Member.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(Set.of(
                        authorityRepository.findByEAuthority(EAuthority.ROLE_UNVERIFIED),
                        authorityRepository.findByEAuthority(EAuthority.ROLE_USER),
                        authorityRepository.findByEAuthority(EAuthority.ROLE_ADMIN)))
                .build();
        memberRepository.save(member);
        return member;
    }
}
