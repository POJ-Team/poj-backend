package com.poj.repository;

import com.poj.entity.member.Authority;
import com.poj.entity.member.EAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByName(EAuthority name);
}