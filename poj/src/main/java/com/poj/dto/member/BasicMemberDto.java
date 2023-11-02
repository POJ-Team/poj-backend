package com.poj.dto.member;

import com.poj.entity.member.EAuthority;
import com.poj.entity.member.Member;
import com.poj.util.AuthorityUtil;
import lombok.Data;

@Data
public class BasicMemberDto {
    private String username;
    private String email;
    private EAuthority authority;

    public BasicMemberDto(Member member) {
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.authority = AuthorityUtil.getAuthority(member);
    }
}
