package com.poj.entity.problem;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemDetail {

    @Id
    @GeneratedValue
    @Column(name = "problem_detail_id")
    private Long id; // ID

    @OneToOne(mappedBy = "problemDetail")
    private Problem problem;

    @NotBlank
    @Column(unique = true)
    private String info; // 문제 설명

    private String inputExample; // input 예시
    private String outputExample; // output 예시

    private Long timeLimit = Long.MAX_VALUE; // 시간 제한(default = Long.MAX_VALUE)
    private Long memoryLimit = 256L; // 메모리 제한. 단위 = mb(default = 256mb)

    @Builder
    public ProblemDetail(String info, String inputExample, String outputExample, Long timeLimit, Long memoryLimit) {
        this.info = info;
        this.inputExample = inputExample;
        this.outputExample = outputExample;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
    }

}
