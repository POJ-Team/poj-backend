package com.poj.dto.board;

import com.poj.entity.board.FreeBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class FreeBoardResponseDTO {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public FreeBoardResponseDTO(FreeBoard freeBoard){
        this.title = freeBoard.getTitle();
        this.content = freeBoard.getContent();
        this.createdAt = freeBoard.getCreatedAt();
        this.updatedAt = freeBoard.getUpdatedAt();
    }
}