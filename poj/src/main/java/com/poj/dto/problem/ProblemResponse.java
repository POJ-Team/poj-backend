package com.poj.dto.problem;

import com.poj.entity.problem.EAvailableLanguage;
import com.poj.entity.problem.EProblemDifficulty;
import com.poj.entity.problem.Problem;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProblemResponse {

    private Long id; // 문제 ID

    private String title; // 제목
    private EProblemDifficulty difficulty; // 난이도
    private Set<EAvailableLanguage> availableLanguage; // 문제에서 사용 가능한 언어 집합

    public static ProblemResponse toDTO(Problem problem){
        return ProblemResponse.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .difficulty(problem.getDifficulty())
                .availableLanguage(problem.getAvailableLanguage())
                .build();
    }
}
