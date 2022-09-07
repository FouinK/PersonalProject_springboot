package com.example.PersonalProject.Board;

import com.example.PersonalProject.DTO.AllBoradResponseDTO;
import com.example.PersonalProject.DTO.CommentResponseDTO;
import com.example.PersonalProject.DTO.CreateBoardRequestDTO;
import com.example.PersonalProject.DTO.OneBoardResponseDTO;
import com.example.PersonalProject.Login.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

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


    /**
     * 게시판 상세내용 응답함수
     * @param board_id
     * @return
     */
    @Override
    public OneBoardResponseDTO getOneBoard(Long board_id) {

        Optional<BoardEntity> boardEntity = boardRepository.findById(board_id);
        List<CommentResponseDTO> commentResponseDTOList = new ArrayList<>();

        OneBoardResponseDTO oneBoardResponseDTO = OneBoardResponseDTO.builder()
                .board_id(boardEntity.get().getId())
                .title(boardEntity.get().getTitle())
                .content(boardEntity.get().getContent())
                .writer(boardEntity.get().getUserInfoEntity().getNickname())
                .createdDate(boardEntity.get().getCreatedDate())
                .viewCnt(boardEntity.get().getViewCnt())
                .build();

        for(int i=0;i<boardEntity.get().getCommentEntity().size();i++){

            CommentResponseDTO individualCommentResponse = CommentResponseDTO.builder()
                    .comment(boardEntity.get().getCommentEntity().get(i).getComment())
                    .writer(boardEntity.get().getCommentEntity().get(i).getWriter())
                    .createdDate(boardEntity.get().getCommentEntity().get(i).getCreatedDate())
                    .build();

            commentResponseDTOList.add(individualCommentResponse);
        }

        oneBoardResponseDTO.setCommentResponseDTOList(commentResponseDTOList);

        return oneBoardResponseDTO;
    }

    @Override
    public void createComment(Map<String, Object> map, PrincipalDetails principalDetails) {

        BoardEntity boardEntity = boardRepository.getOne(Long.valueOf(String.valueOf(map.get("team_id"))));

        CommentEntity commentEntity = CommentEntity.builder()
                .writer(principalDetails.getUserInfo().getNickname())
                .comment(String.valueOf(map.get("comment")))
                .boardEntity(boardEntity)
                .build();

        commentRepository.save(commentEntity);
    }

//    @Override
//    public List<CommentResponseDTO> getCommentList(Long board_id) {
//        List<CommentResponseDTO> commentResponseDTOList = new ArrayList<>();
//        List<CommentEntity> commentEntityList = commentRepository.findByBoardEntity_Id(board_id);
//        for (int i = 0; i < commentEntityList.size(); i++) {
//            CommentResponseDTO individualCommentResponse = CommentResponseDTO.builder()
//                    .comment(commentEntityList.get(i).getComment())
//                    .writer(commentEntityList.get(i).getWriter())
//                    .createdDate(commentEntityList.get(i).getCreatedDate())
//                    .build();
//
//            commentResponseDTOList.add(individualCommentResponse);
//        }
//        return commentResponseDTOList;
//    }

}
