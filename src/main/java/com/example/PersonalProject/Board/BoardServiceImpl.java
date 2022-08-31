package com.example.PersonalProject.Board;

import com.example.PersonalProject.DTO.AllBoradResponseDTO;
import com.example.PersonalProject.DTO.CreateBoardRequestDTO;
import com.example.PersonalProject.Login.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    /**
     * 게시판 작성 함수
     * @param createBoardRequestDTO
     * @param principalDetails
     */
    @Override
    public void createBoard(CreateBoardRequestDTO createBoardRequestDTO, PrincipalDetails principalDetails) {

        BoardEntity boardEntity = BoardEntity.builder()
                .title(createBoardRequestDTO.getTitle())
                .content(createBoardRequestDTO.getContent())
                .viewCnt(0)
                .userInfoEntity(principalDetails.getUserInfo())
                .build();

        boardRepository.save(boardEntity);
    }


    /**
     * 전체 게시판 응답 함수
     * @param pageable
     * @return
     */
    @Override
    public Page<AllBoradResponseDTO> allBoard(Pageable pageable) {

        Page<BoardEntity> boardEntity = boardRepository.findAll(pageable);

        Page<AllBoradResponseDTO> allBoradResponseDTO = boardEntity.map(
                board -> AllBoradResponseDTO.builder()
                        .board_id(board.getId())
                        .title(board.getTitle())
                        .viewCnt(board.getViewCnt())
                        .writer(board.getUserInfoEntity().getNickname())
                        .createdDate(board.getCreatedDate())
                        .build()
        );
        return allBoradResponseDTO;
    }
}
