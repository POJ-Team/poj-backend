package com.poj.dto.problem;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
Problem을 생성하는 request의 형식
 */

@Data
public class CreateProblemRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String info;
    private String inputExample;
    private String outputExample;
}
