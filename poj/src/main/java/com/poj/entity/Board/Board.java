//게시글 모음 X, 게시글 자체 클래스
package com.poj.entity.Board;
import com.poj.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {//지금은 추상 클래스로 확장할 때가 아님. 하위 클래스가 만들어지고 나서 함.
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @NotBlank
    private String title;//글의 제목

    @NotBlank
    @Column(unique = true)
    private String username;//작성자

    @NotBlank
    private String text;//텍스트 저장

    @NotBlank
    private int sawed;//조회수
    
    @Builder
    public Board(String title, String text, String username){//생성자
        this.title = title;
        this.text = text;
        this.username = username;
        this.sawed = 0;
    }
    public String getAuthor() {return this.username;}//작성자 불러오기
    public void upSawed(){sawed++;}//조회수 증가
    public String getTitle() {return this.title;}//제목 불러오기
    public int getSawed() {return this.sawed;}//글 조회수 불러오기
    public String getText() {return this.text;}//글 내용 불러오기
}
