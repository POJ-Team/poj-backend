package com.poj.service.auth;

import com.poj.dto.auth.LoginRequest;
import com.poj.dto.auth.LoginSuccess;
import com.poj.dto.jwt.AtRt;
import com.poj.entity.member.Member;
import com.poj.security.jwt.AtRtService;
import com.poj.security.userdetails.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final AtRtService atRtService;

    public LoginSuccess login(LoginRequest request) {
        // 여기서 생성된 authentication 안에 들어 있는 member 는 실제 DB 에서 가져온 member 입니다.
        // 관련해서는 UserDetailsServiceImpl 를 참고해주세요.
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetailsImpl userDetails = (UserDetailsImpl) authenticate.getPrincipal();
        Member member = userDetails.getMember();
        AtRt atRt = atRtService.createAtRt(authenticate);

        return new LoginSuccess(member, atRt);
    }
}
