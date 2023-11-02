package com.poj.dto.problem;

import com.poj.entity.problem.EAvailableLanguage;
import com.poj.entity.problem.EProblemDifficulty;
import com.poj.entity.problem.Problem;
import lombok.Data;

import java.util.Set;

@Data
public class ProblemResponse {

    private String title; // 제목
    private EProblemDifficulty difficulty; // 난이도
    private Set<EAvailableLanguage> availableLanguage; // 문제에서 사용 가능한 언어 집합

    public ProblemResponse(String title, EProblemDifficulty difficulty, Set<EAvailableLanguage> availableLanguage) {
        this.title = title;
        this.difficulty = difficulty;
        this.availableLanguage = availableLanguage;
    }
    
    public static ProblemResponse toDTO(Problem problem){
        return new ProblemResponse(problem.getTitle(), problem.getDifficulty(), problem.getAvailableLanguage());
    }
}
