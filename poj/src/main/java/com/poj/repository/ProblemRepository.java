package com.poj.repository;

import com.poj.entity.problem.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
Problem을 저장하는 JPARepository
 */
public interface ProblemRepository extends JpaRepository<Problem, Long> {

}
