package com.poj.entity.problem;

import com.poj.entity.BaseEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "problem_id")
    private Long id; // ID

    @OneToOne(mappedBy = "problem", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ProblemDetail problemDetail;

//    @NotBlank
//    @Column(unique = true)
//    private Long index; // 번호. 생성로직 필요

    @NotBlank
    @Column(unique = true)
    private String title; // 제목

    private Long submitNumber = 0L; // 제출한 사람 수
    private Long passNumber = 0L; // 통과한 사람 수

     private EProblemDifficulty difficulty; // 난이도
     private Set<EAvailableLanguage> availableLanguage; // 문제에서 사용 가능한 언어 집합

    private Long registeredTime; // 문제를 등록한 시간

    @Builder
    public Problem(String title,
                    EProblemDifficulty difficulty,
                   Set<EAvailableLanguage> availableLanguage,
                   ProblemDetail problemDetail) {
        this.title = title;
        this.difficulty = difficulty;
        this.availableLanguage = availableLanguage;
        this.problemDetail = problemDetail;

        problemDetail.setProblem(this);

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
