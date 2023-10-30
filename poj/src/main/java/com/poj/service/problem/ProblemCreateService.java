package com.poj.service.problem;

import com.poj.dto.problem.ProblemDetailRequest;
import com.poj.dto.problem.ProblemRequest;
import com.poj.entity.problem.Problem;
import com.poj.entity.problem.ProblemDetail;
import com.poj.repository.problem.ProblemRepository;
import com.poj.repository.problem.ProblemRepositoryQuerydsl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Problem, ProblemDetail에 대한 request를 받아 새로운 문제를 생성합니다.
 */

@RequiredArgsConstructor
@Transactional
@Service
public class ProblemCreateService {
    private final ProblemRepository problemRepository;
    private final ProblemRepositoryQuerydsl problemRepositoryQuerydsl;

    public Long CreateProblem(ProblemRequest request, ProblemDetailRequest request_d){
        // Detail을 먼저 만들고 이후 Problem에 이를 지정하는 방식으로 생성합니다.

        ProblemDetail problemDetail = ProblemDetail.builder().
                info(request_d.getInfo()).
                inputExample(request_d.getInputExample()).
                outputExample(request_d.getOutputExample()).
                timeLimit(request_d.getTimeLimit()).
                memoryLimit(request_d.getMemoryLimit()).build();

        Problem problem = Problem.builder().
                title(request.getTitle()).
                difficulty(request.getDifficulty()).
                availableLanguage(request.getAvailableLanguage()).
                problemDetail(problemDetail).build();

        problemRepository.save(problem);

        return problem.getId(); // ProblemDetail의 ID는 리턴하고 있지 않습니다.
    }
}
