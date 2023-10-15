package com.poj.repository.problem;

import com.poj.entity.problem.Problem;
import com.poj.entity.problem.QProblem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.poj.entity.problem.QProblem.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProblemRepositoryQuerydslImpl implements ProblemRepositoryQuerydsl {
    private final JPAQueryFactory query;

    @Override
    public Optional<Problem> findByIdWithProblemDetail(Long id) {
        return Optional.ofNullable(
                query
                        .selectFrom(problem)
                        .leftJoin(problem.problemDetail)
                        .fetchJoin()
                        .where(problem.id.eq(id))
                        .fetchFirst()
        );
    }
}
