package com.poj.repository.problem;

import com.poj.entity.problem.Problem;

import java.util.Optional;

public interface ProblemRepositoryQuerydsl {
    Optional<Problem> findByIdWithProblemDetail(Long id);
}
