package com.poj.repository.member;

import com.poj.entity.member.Authority;
import com.poj.entity.member.EAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("select a from Authority a where a.eAuthority = :eAuthority")
    Authority findByEAuthority(EAuthority eAuthority);
}