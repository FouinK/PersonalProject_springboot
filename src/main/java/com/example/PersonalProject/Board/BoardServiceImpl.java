package com.example.PersonalProject.Board;

import com.example.PersonalProject.DTO.AllBoradResponseDTO;
import com.example.PersonalProject.DTO.CreateBoardRequestDTO;
import com.example.PersonalProject.DTO.OneBoardResponseDTO;
import com.example.PersonalProject.Login.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public OneBoardResponseDTO getOneBoard(Long team_id) {

        Optional<BoardEntity> boardEntity = boardRepository.findById(team_id);

        OneBoardResponseDTO oneBoardResponseDTO = OneBoardResponseDTO.builder()
                .board_id(boardEntity.get().getId())
                .title(boardEntity.get().getTitle())
                .content(boardEntity.get().getContent())
                .writer(boardEntity.get().getUserInfoEntity().getNickname())
                .createdDate(boardEntity.get().getCreatedDate())
                .viewCnt(boardEntity.get().getViewCnt())
                .build();

        return oneBoardResponseDTO;
    }
}
