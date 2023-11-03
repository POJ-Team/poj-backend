package com.poj.dto.problem;

import com.poj.entity.problem.EAvailableLanguage;
import com.poj.entity.problem.EProblemDifficulty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

/**
 * 문제를 생성할 때 사용하는 Request입니다.
 */
@Data
public class ProblemCreateAndUpdateRequest {

    @NotBlank
    private String title; // 제목

    @Enumerated(EnumType.STRING)
    private EProblemDifficulty difficulty; // 난이도

    @Enumerated(EnumType.STRING)
    private Set<EAvailableLanguage> availableLanguage; // 문제에서 사용 가능한 언어 집합

    @NotBlank
    private String info; // 문제 설명

    private String inputExample; // input 예시
    private String outputExample; // output 예시

    private Long timeLimit; // 시간 제한(default = Long.MAX_VALUE)
    private Long memoryLimit; // 메모리 제한. 단위 = mb(default = 256mb)
}
