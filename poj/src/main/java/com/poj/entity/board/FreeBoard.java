package com.poj.entity.board;

import com.poj.dto.board.freeboardDTO.FreeBoardRequestDTO;
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
public class FreeBoard extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "free_board_id")
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;//글의 내용

    private int viewCount;

    @NotBlank
    private String author;//author

    @Builder
    public FreeBoard(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
        this.viewCount = 0;
    }

    public FreeBoard(FreeBoardRequestDTO freeBoardRequestDTO){
        this.title = freeBoardRequestDTO.getTitle();
        this.content = freeBoardRequestDTO.getContent();
        this.author = freeBoardRequestDTO.getAuthor();
        this.viewCount = 0;
    }

    public void update(FreeBoardRequestDTO freeBoardRequestDTO){
        this.title = freeBoardRequestDTO.getTitle();
        this.content = freeBoardRequestDTO.getContent();
        this.author = freeBoardRequestDTO.getAuthor();
    }

    public void upViewCount(){
        viewCount++;
    }
}