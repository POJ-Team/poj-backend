package com.poj.dto.board.noticeboardDTO;

import com.poj.entity.board.NoticeBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NoticeBoardListResponseDTO {
    private String title;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public NoticeBoardListResponseDTO(NoticeBoard noticeBoard){
        this.title = noticeBoard.getTitle();
        this.author = noticeBoard.getAuthor();
        this.createdAt = noticeBoard.getCreatedAt();
        this.updatedAt = noticeBoard.getUpdatedAt();
    }

    public NoticeBoardListResponseDTO (Optional<NoticeBoard> noticeBoardOptional){
        this.title = noticeBoardOptional.get().getTitle();
        this.author = noticeBoardOptional.get().getAuthor();
        this.createdAt = noticeBoardOptional.get().getCreatedAt();
        this.updatedAt = noticeBoardOptional.get().getUpdatedAt();
    }
}