package com.poj.dto.board.freeboardDTO;

import com.poj.entity.board.FreeBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FreeBoardListResponseDTO {
    private String title;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public FreeBoardListResponseDTO(FreeBoard freeBoard){
        this.title = freeBoard.getTitle();
        this.author = freeBoard.getAuthor();
        this.createdAt = freeBoard.getCreatedAt();
        this.updatedAt = freeBoard.getUpdatedAt();
    }

    public FreeBoardListResponseDTO (Optional<FreeBoard> freeBoardOptional){
        this.title = freeBoardOptional.get().getTitle();
        this.author = freeBoardOptional.get().getAuthor();
        this.createdAt = freeBoardOptional.get().getCreatedAt();
        this.updatedAt = freeBoardOptional.get().getUpdatedAt();
    }
}