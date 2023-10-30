package com.poj.dto.problem;

import com.poj.entity.problem.EAvailableLanguage;
import com.poj.entity.problem.EProblemDifficulty;
import com.poj.entity.problem.ProblemDetail;
import lombok.Data;

import java.util.Set;

@Data
public class ProblemResponse {

    private String title; // 제목
    private EProblemDifficulty difficulty; // 난이도
    private Set<EAvailableLanguage> availableLanguage; // 문제에서 사용 가능한 언어 집합
    private ProblemDetail problemDetail; // 문제의 상세 정보

    public ProblemResponse(String title, EProblemDifficulty difficulty, Set<EAvailableLanguage> availableLanguage, ProblemDetail problemDetail) {
        this.title = title;
        this.difficulty = difficulty;
        this.availableLanguage = availableLanguage;
        this.problemDetail = problemDetail; // problemDetail에 대한 response도 필요할까요?
    }
}
