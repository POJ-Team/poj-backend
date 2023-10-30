package com.poj.service.problem;

import com.poj.repository.problem.ProblemRepository;
import com.poj.repository.problem.ProblemRepositoryQuerydsl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class ProblemDeleteService {
    private final ProblemRepository problemRepository;
    private final ProblemRepositoryQuerydsl problemRepositoryQuerydsl;

    public void DeleteProblem(Long ProblemID){
        if(problemRepository.existsById(ProblemID)){
            problemRepository.deleteById(ProblemID);
        }
    }
}
