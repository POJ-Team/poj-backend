package com.poj.dto.problem;

import lombok.Data;

/**
 * Problem Read 작업시 사용하는 Request입니다.
 */
@Data
public class ProblemReadRequest {
    int pageNumber; // 페이지 번호
    int size; // 페이지에 표시되는 개수
}
