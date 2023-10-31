package com.poj.service.problem;

import com.poj.entity.problem.EAvailableLanguage;
import com.poj.entity.problem.EProblemDifficulty;
import com.poj.entity.problem.Problem;
import com.poj.repository.problem.ProblemRepository;
import com.poj.repository.problem.ProblemRepositoryQuerydsl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ID를 기준으로 Problem을 검색합니다.
 */


@RequiredArgsConstructor
@Transactional
@Service
public class ProblemReadService {

    private final ProblemRepository problemRepository;
    private final ProblemRepositoryQuerydsl problemRepositoryQuerydsl;

    public Problem findProblemByIdWithDetail(Long id){
        Optional<Problem> problem = problemRepositoryQuerydsl.findByIdWithProblemDetail(id);
        return problem.orElse(null);
    }

    public Problem findProblemById(Long id){
        Optional<Problem> problem = problemRepository.findById(id);
        return problem.orElse(null);
    }


    public Page<Problem> findProblemByTitle(String title, int pagenumber, int pagesize){
        PageRequest pageRequest = PageRequest.of(pagenumber,pagesize);
        Page<Problem> problemPage = problemRepository.findAllByTitleContains(title, pageRequest);
        return problemPage;
    }

    public Page<Problem> findProblemByLanguage(EAvailableLanguage language, int pagenumber, int pagesize){
        PageRequest pageRequest = PageRequest.of(pagenumber,pagesize);
        Page<Problem> problemPage = problemRepository.findAllByAvailableLanguage(language, pageRequest);
        return problemPage;
    }

    public Page<Problem> findProblemByDifficulty(EProblemDifficulty difficulty, int pagenumber, int pagesize){
        PageRequest pageRequest = PageRequest.of(pagenumber,pagesize);
        Page<Problem> problemPage = problemRepository.findAllByDifficulty(difficulty, pageRequest);
        return problemPage;
    }
}
