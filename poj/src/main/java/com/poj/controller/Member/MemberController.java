package com.poj.controller.Member;


import com.poj.entity.member.Member;
import com.poj.security.userdetails.UserDetailsImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {
    // Member 객체가 성공적으로 저장됬는지 확인하기 위한 test 용 api 입니다.
    @GetMapping("/profile")
    public ResponseEntity<Profile> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        Member member = principal.getMember();
        return ResponseEntity.ok(new Profile(member));
    }

    @Data
    public static class Profile {
        private String username;
        private String email;

        public Profile(Member member) {
            this.username = member.getUsername();
            this.email = member.getEmail();
        }
    }
}
