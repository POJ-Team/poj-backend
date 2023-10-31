package com.poj.service.problem;

import com.poj.dto.problem.ProblemDetailRequest;
import com.poj.dto.problem.ProblemRequest;
import com.poj.entity.problem.Problem;
import com.poj.repository.problem.ProblemRepository;
import com.poj.repository.problem.ProblemRepositoryQuerydsl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 문제의 내용을 수정합니다.
 */


@RequiredArgsConstructor
@Transactional
@Service
public class ProblemUpdateService {
    private final ProblemRepository problemRepository;
    private final ProblemRepositoryQuerydsl problemRepositoryQuerydsl;

    public void UpdateProblem(Long ID, ProblemRequest problemRequest){
        if(problemRepository.findById(ID).isPresent()){
            Problem problem = problemRepository.findById(ID).get();
            problem.update(problemRequest);
            problemRepository.save(problem);
        }
    }

    public void UpdateProblemWithDetail(Long ID, ProblemRequest problemRequest, ProblemDetailRequest problemDetailRequest){
        if(problemRepository.findById(ID).isPresent()){
            Problem problem = problemRepository.findById(ID).get();
            problem.updateWithDetail(problemRequest, problemDetailRequest);
            problemRepository.save(problem);
        }
    }


}
