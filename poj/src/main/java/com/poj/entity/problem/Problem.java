package com.poj.entity.problem;

import com.poj.entity.BaseEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Long index; // 번호

    @NotBlank
    private String title; // 제목

    @NotBlank
    @Column(unique = true)
    private String info; // 문제 설명

    private String inputExample; // input 예시
    private String outputExample; // output 예시

    private Long submitNumber = 0L; // 제출한 사람 수
    private Long passNumber = 0L; // 통과한 사람 수
    private Double passSubmitRatio; // 통과 비율


    @Builder
    public Problem(String title, String info, String inputExample, String outputExample) {
        this.title = title;
        this.info = info;
        this.inputExample = inputExample;
        this.outputExample = outputExample;
    }

    public void addSubmitNum(){
        submitNumber++;
        passSubmitRatio = (double)passNumber / submitNumber;
    }

    public void addPassNum(){
        passNumber++;
        addSubmitNum();
    }
}
