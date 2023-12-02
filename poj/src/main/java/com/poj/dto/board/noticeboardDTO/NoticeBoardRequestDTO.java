package com.poj.dto.board.noticeboardDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NoticeBoardRequestDTO {
    private String title;
    private String content;
    private String author;
}
