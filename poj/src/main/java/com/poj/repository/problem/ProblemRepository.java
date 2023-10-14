package com.poj.repository.problem;

import com.poj.entity.problem.Problem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
Problem을 저장하는 JPARepository
 */
public interface ProblemRepository extends JpaRepository<Problem, Long> {

    Optional<Problem> findById(Long id); // problem only

    @EntityGraph(attributePaths = {"problemDetail"})
    Optional<Problem> findWithProblemDetailById(Long id); // fetch join

    // paging
    // 필요한 검색기능
    // 난이도, 이름, 날짜순, 통과비율
}
