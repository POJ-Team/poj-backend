package com.poj.dto.board.noticeboardDTO;

import com.poj.entity.board.NoticeBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class NoticeBoardResponseDTO {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public NoticeBoardResponseDTO(NoticeBoard noticeBoard) {
        this.title = noticeBoard.getTitle();
        this.content = noticeBoard.getContent();
        this.createdAt = noticeBoard.getCreatedAt();
        this.updatedAt = noticeBoard.getUpdatedAt();
    }
}