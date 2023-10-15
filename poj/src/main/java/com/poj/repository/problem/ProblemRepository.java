package com.poj.repository.problem;

import com.poj.entity.problem.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
Problem을 저장하는 JPARepository
 */
public interface ProblemRepository extends JpaRepository<Problem, Long>, ProblemRepositoryQuerydsl{

    Optional<Problem> findById(Long id); // problem only

    // paging
    // 필요한 검색기능
    // 난이도, 이름, 날짜순, 통과비율
}
