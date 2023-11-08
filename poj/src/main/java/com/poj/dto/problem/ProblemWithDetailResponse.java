package com.poj.dto.problem;

import com.poj.entity.problem.EAvailableLanguage;
import com.poj.entity.problem.EProblemDifficulty;
import com.poj.entity.problem.Problem;
import com.poj.entity.problem.ProblemDetail;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProblemWithDetailResponse {

    private Long id; // 문제 id

    private String title; // 제목
    private EProblemDifficulty difficulty; // 난이도
    private Set<EAvailableLanguage> availableLanguage; // 문제에서 사용 가능한 언어 집합

    private ProblemDetail problemDetail; // 문제의 상세 정보

    public static ProblemWithDetailResponse toDTO(Problem problem){
        return ProblemWithDetailResponse.builder()
                .id(problem.getId())
                .title(problem.getTitle())
                .difficulty(problem.getDifficulty())
                .availableLanguage(problem.getAvailableLanguage())
                .problemDetail(problem.getProblemDetail())
                .build();

    }
}
