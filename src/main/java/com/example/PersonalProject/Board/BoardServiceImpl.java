package com.example.PersonalProject.Board;

import com.example.PersonalProject.DTO.AllBoradResponseDTO;
import com.example.PersonalProject.DTO.CreateBoardRequestDTO;
import com.example.PersonalProject.Login.PrincipalDetails;
import com.example.PersonalProject.User.UserRepository;
import lombok.RequiredArgsConstructor;
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
     * @return
     */
    @Override
    public List<AllBoradResponseDTO> allBoard() {

        List<BoardEntity> boardEntity = boardRepository.findAll();
        List<AllBoradResponseDTO> allBoradResponseDTO = new ArrayList<>();

        for (BoardEntity entity : boardEntity) {

            AllBoradResponseDTO individualAllBoradResponseDTO = AllBoradResponseDTO.builder()
                    .board_id(entity.getId())
                    .title(entity.getTitle())
                    .viewCnt(entity.getViewCnt())
                    .writer(entity.getUserInfoEntity().getNickname())
                    .createdDate(entity.getCreatedDate())
                    .build();

            allBoradResponseDTO.add(individualAllBoradResponseDTO);
        }

        return allBoradResponseDTO;
    }
}
