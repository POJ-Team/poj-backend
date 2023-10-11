package com.poj.factory.problem;

import com.poj.dto.problem.CreateProblemRequest;
import com.poj.entity.problem.Problem;
import com.poj.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
Problem을 생성하는 request가 들어오면 problem을 생성하고 이를 repository에 넣는다.
 */

@Component
@RequiredArgsConstructor
public class ProblemFactory {

    private final ProblemRepository problemRepository;

    public Problem Create(CreateProblemRequest request){
        Problem problem = Problem.builder()
                .title(request.getTitle())
                .info(request.getInfo())
                .inputExample(request.getInputExample())
                .outputExample(request.getOutputExample())
                .build();

        problemRepository.save(problem);

        return problem;
    }
}
