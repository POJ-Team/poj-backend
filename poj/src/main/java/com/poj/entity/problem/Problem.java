package com.poj.entity.problem;

import com.poj.entity.BaseEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "problem_id")
    private Long id; // ID

    @NotBlank
    @Column(unique = true)
    private Long index; // 번호.

    @NotBlank
    private String title; // 제목

    @NotBlank
    @Column(unique = true)
    private String info; // 문제 설명

    private String inputExample; // input 예시
    private String outputExample; // output 예시

    private Long submitNumber = 0L; // 제출한 사람 수
    private Long passNumber = 0L; // 통과한 사람 수

    private Long timeLimit = Long.MAX_VALUE; // 시간 제한(default = Long.MAX_VALUE)
    private Long memoryLimit = 256L; // 메모리 제한. 단위 = mb(default = 256mb)

    private EProblemDifficulty difficulty; // 난이도
    private List<EAvailableLanguage> availableLanguage; // 문제에서 사용 가능한 언어 리스트(리스트??)

    private Long registeredTime; // 문제를 등록한 시간


    @Builder
    public Problem(String title, String info, String inputExample, String outputExample,
                   Long timeLimit, Long memoryLimit, EProblemDifficulty difficulty, List<EAvailableLanguage> availableLanguage) {
        this.title = title;
        this.info = info;
        this.inputExample = inputExample;
        this.outputExample = outputExample;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.difficulty = difficulty;
        this.availableLanguage = availableLanguage;
        // 너무 긴거 같음

        registeredTime = Date.from(Instant.now()).getTime(); // 현재 시간 등록
    }

    public void addSubmitNum(){
        submitNumber++;
    }

    public void addPassNum(){
        passNumber++;
    }

    public double getPassRatio() {
        return (double)passNumber / submitNumber;
    }
}
