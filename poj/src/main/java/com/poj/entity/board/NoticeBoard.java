package com.poj.entity.board;

import com.poj.dto.board.freeboardDTO.FreeBoardRequestDTO;
import com.poj.dto.board.noticeboardDTO.NoticeBoardRequestDTO;
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
public class NoticeBoard extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "notice_board_id")
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;//글의 내용

    private int viewCount;

    @NotBlank
    private String author;//author

    @Builder
    public NoticeBoard(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
        this.viewCount = 0;
    }

    public NoticeBoard(NoticeBoardRequestDTO noticeBoardRequestDTO){
        this.title = noticeBoardRequestDTO.getTitle();
        this.content = noticeBoardRequestDTO.getContent();
        this.author = noticeBoardRequestDTO.getAuthor();
        this.viewCount = 0;
    }

    public void update(NoticeBoardRequestDTO noticeBoardRequestDTO){
        this.title = noticeBoardRequestDTO.getTitle();
        this.content = noticeBoardRequestDTO.getContent();
        this.author = noticeBoardRequestDTO.getAuthor();
    }

    public void upViewCount(){
        viewCount++;
    }
}
