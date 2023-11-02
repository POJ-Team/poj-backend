package com.poj.service.problem;

import com.poj.dto.problem.ProblemCreateRequest;
import com.poj.entity.problem.Problem;
import com.poj.entity.problem.ProblemDetail;
import com.poj.repository.problem.ProblemRepository;
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

    public Long CreateProblem(ProblemCreateRequest problemCreateRequest){
        // Detail을 먼저 만들고 이후 Problem에 이를 지정하는 방식으로 생성합니다.

        ProblemDetail problemDetail = ProblemDetail.builder().
                info(problemCreateRequest.getInfo()).
                inputExample(problemCreateRequest.getInputExample()).
                outputExample(problemCreateRequest.getOutputExample()).
                timeLimit(problemCreateRequest.getTimeLimit()).
                memoryLimit(problemCreateRequest.getMemoryLimit()).build();

        Problem problem = Problem.builder().
                title(problemCreateRequest.getTitle()).
                difficulty(problemCreateRequest.getDifficulty()).
                availableLanguage(problemCreateRequest.getAvailableLanguage()).
                problemDetail(problemDetail).build();

        problemRepository.save(problem);

        return problem.getId();
    }
}
