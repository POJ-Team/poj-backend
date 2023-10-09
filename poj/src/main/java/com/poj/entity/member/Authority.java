package com.poj.entity.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {
    @Id
    @GeneratedValue
    @Column(name = "authority_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private EAuthority name;

    public String getName() {
        return name.name();
    }
    public Authority(EAuthority name) {
        this.name = name;
    }
}
