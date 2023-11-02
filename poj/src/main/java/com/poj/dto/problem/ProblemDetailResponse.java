package com.poj.dto.problem;

import jakarta.validation.constraints.NotBlank;

public class ProblemDetailResponse {
    @NotBlank
    private String info; // 문제 설명

    private String inputExample; // input 예시
    private String outputExample; // output 예시

    private Long timeLimit; // 시간 제한(default = Long.MAX_VALUE)
    private Long memoryLimit; // 메모리 제한. 단위 = mb(default = 256mb)

    public ProblemDetailResponse(String info, String inputExample, String outputExample, Long timeLimit, Long memoryLimit) {
        this.info = info;
        this.inputExample = inputExample;
        this.outputExample = outputExample;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }
}
