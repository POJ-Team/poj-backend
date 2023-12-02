package com.poj.dto.board.freeboardDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FreeBoardRequestDTO {
    private String title;
    private String content;
    private String author;
}