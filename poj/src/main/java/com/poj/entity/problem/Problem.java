package com.poj.entity.problem;

import com.poj.entity.BaseEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자에게 제공되는 문제.
 * 번호, 제목, 설명, input/output 예시, 제출한 사람 수, 통과한 사람 수, 통과 비율
 * 사용자가 문제를 제출할 때마다 addSumitNum을 호출. 만약 맞으면 addPassNum을 이후 호출
 */
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
    private Long index; // 번호. 새로운 문제가 추가될 때마다 번호가 증가해야 하는데 아직 그 기능이 없음.

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
