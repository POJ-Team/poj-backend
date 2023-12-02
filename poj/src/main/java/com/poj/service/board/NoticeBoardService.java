package com.poj.service.board;

import java.util.*;
import com.poj.dto.board.noticeboardDTO.*;
import com.poj.entity.board.NoticeBoard;
import com.poj.exception.board.BoardServiceException;
import com.poj.repository.board.NoticeBoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class NoticeBoardService {
    private final NoticeBoardRepository noticeBoardRepository;

    public NoticeBoardResponseDTO createBoard(NoticeBoardRequestDTO noticeBoardRequestDTO){
        NoticeBoard freeBoard = new NoticeBoard(noticeBoardRequestDTO);
        noticeBoardRepository.save(freeBoard);
        return new NoticeBoardResponseDTO(freeBoard);
    }

    public List<NoticeBoardListResponseDTO> findAllNoticeBoard() {
        try{
            List<NoticeBoard> boardList = noticeBoardRepository.findAll();
            List<NoticeBoardListResponseDTO> responseDTOList = new ArrayList<>();
            for (NoticeBoard freeBoard: boardList){
                responseDTOList.add(
                        new NoticeBoardListResponseDTO(freeBoard)
                );
            }
            return responseDTOList;
        } catch (Exception e){
            throw new BoardServiceException("게시물이 존재하지 않습니다.");
        }
    }

    public NoticeBoardResponseDTO findOneBoard(Long id){
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );
        return new NoticeBoardResponseDTO(noticeBoard);
    }

    public Long updateBoard(Long id, NoticeBoardRequestDTO noticeBoardRequestDTO){
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 ID가 존재하지 않습니다.")
        );
        noticeBoard.update(noticeBoardRequestDTO);
        return noticeBoard.getId();
    }
    public Long deleteBoard(Long id){
        noticeBoardRepository.deleteById(id);
        return id;
    }
}
