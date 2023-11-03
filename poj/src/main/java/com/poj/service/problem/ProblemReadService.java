package com.poj.service.problem;

import com.poj.dto.problem.ProblemReadRequest;
import com.poj.dto.problem.ProblemResponse;
import com.poj.entity.problem.EAvailableLanguage;
import com.poj.entity.problem.EProblemDifficulty;
import com.poj.entity.problem.Problem;
import com.poj.repository.problem.ProblemRepository;
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

    public Problem getProblemByIdWithDetail(Long id){
        Optional<Problem> problem = problemRepository.findByIdWithProblemDetail(id);
        return problem.orElse(null);
    }

    public Problem getProblemById(Long id){
        Optional<Problem> problem = problemRepository.findById(id);
        return problem.orElse(null);
    }


    public Page<ProblemResponse> getProblemsByTitle(String title, ProblemReadRequest problemReadRequest){
        PageRequest pageRequest = PageRequest.of(problemReadRequest.getPageNumber(), problemReadRequest.getSize());
        Page<Problem> problemPage = problemRepository.findAllByTitleContains(title, pageRequest);
        return problemPage.map(ProblemResponse::toDTO);
    }

    public Page<ProblemResponse> getProblemsByLanguage(EAvailableLanguage language, ProblemReadRequest problemReadRequest){
        PageRequest pageRequest = PageRequest.of(problemReadRequest.getPageNumber(), problemReadRequest.getSize());
        Page<Problem> problemPage = problemRepository.findAllByAvailableLanguage(language, pageRequest);
        return problemPage.map(ProblemResponse::toDTO);
    }

    public Page<ProblemResponse> getProblemsByDifficulty(EProblemDifficulty difficulty, ProblemReadRequest problemReadRequest){
        PageRequest pageRequest = PageRequest.of(problemReadRequest.getPageNumber(), problemReadRequest.getSize());
        Page<Problem> problemPage = problemRepository.findAllByDifficulty(difficulty, pageRequest);
        return problemPage.map(ProblemResponse::toDTO);
    }
}
