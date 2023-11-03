package com.poj.service.problem;

import com.poj.dto.problem.ProblemCreateAndUpdateRequest;
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

    public Long CreateProblem(ProblemCreateAndUpdateRequest problemCreateAndUpdateRequest){
        // Detail을 먼저 만들고 이후 Problem에 이를 지정하는 방식으로 생성합니다.

        ProblemDetail problemDetail = ProblemDetail.builder().
                info(problemCreateAndUpdateRequest.getInfo()).
                inputExample(problemCreateAndUpdateRequest.getInputExample()).
                outputExample(problemCreateAndUpdateRequest.getOutputExample()).
                timeLimit(problemCreateAndUpdateRequest.getTimeLimit()).
                memoryLimit(problemCreateAndUpdateRequest.getMemoryLimit()).build();

        Problem problem = Problem.builder().
                title(problemCreateAndUpdateRequest.getTitle()).
                difficulty(problemCreateAndUpdateRequest.getDifficulty()).
                availableLanguage(problemCreateAndUpdateRequest.getAvailableLanguage()).
                problemDetail(problemDetail).build();

        problemRepository.save(problem);

        return problem.getId();
    }
}
