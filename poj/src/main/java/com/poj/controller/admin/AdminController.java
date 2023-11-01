package com.poj.controller.admin;

import com.poj.dto.member.BasicMemberDto;
import com.poj.entity.member.Authority;
import com.poj.entity.member.EAuthority;
import com.poj.repository.member.AuthorityRepository;
import com.poj.repository.member.MemberRepository;
import com.poj.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final MemberRepository memberRepository;

    @GetMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestParam Long memberId) {
        adminService.upgradeToUser(memberId);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/members")
    public ResponseEntity<Page<BasicMemberDto>> members(
            @RequestParam(required = false) EAuthority authority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(memberRepository.findAllByAuthority(authority, page, size));
    }
}
