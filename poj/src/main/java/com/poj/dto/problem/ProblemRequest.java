package com.poj.dto.problem;

import com.poj.entity.problem.EAvailableLanguage;
import com.poj.entity.problem.EProblemDifficulty;
import com.poj.entity.problem.ProblemDetail;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class ProblemRequest {

    @NotBlank
    private String title; // 제목

    @Enumerated(EnumType.STRING)
    private EProblemDifficulty difficulty; // 난이도

    @Enumerated(EnumType.STRING)
    private Set<EAvailableLanguage> availableLanguage; // 문제에서 사용 가능한 언어 집합

    private ProblemDetail problemDetail; // 문제의 상세 정보
}
