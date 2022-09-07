package com.example.PersonalProject.Board;

import com.example.PersonalProject.DTO.AllBoradResponseDTO;
import com.example.PersonalProject.DTO.CreateBoardRequestDTO;
import com.example.PersonalProject.DTO.OneBoardResponseDTO;
import com.example.PersonalProject.Login.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시판 작성 함수
     * @param principalDetails
     * @param createBoardRequestDTO
     * @return
     */
    @PostMapping("/api/create/board")
    public ResponseEntity<?> createBoard(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody CreateBoardRequestDTO createBoardRequestDTO) {
        boardService.createBoard(createBoardRequestDTO, principalDetails);
        return ResponseEntity.ok("게시판 작성 완료");
    }

    /**
     * 전체 게시판 응답 매핑 함수 (조회순 정렬)
     * @param pageable
     * @return
     */
    @GetMapping("/api/board")
    public ResponseEntity<?> getAllBoard(
            @PageableDefault(size = 10,sort = "viewCnt",direction = Sort.Direction.DESC) Pageable pageable) {

        Page<AllBoradResponseDTO> allBoradResponseDTOList = boardService.allBoard(pageable);

        return ResponseEntity.ok(allBoradResponseDTOList);
    }

    /**
     * 전체 게시판 응답 매핑 함수 (최신순 정렬)
     * @param pageable
     * @return
     */
    @GetMapping("/api/board_created_date_range")
    public ResponseEntity<?> boardRangeViewCnt(
            @PageableDefault(size = 10,sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageable) {

        Page<AllBoradResponseDTO> allBoradResponseDTOList = boardService.allBoard(pageable);


        return ResponseEntity.ok(allBoradResponseDTOList);
    }

    /**
     * 게시글 상세내용 응답 매핑 함수
     * @param board_id
     * @return
     */
    @GetMapping("/api/get_board")
    public ResponseEntity<?> getBoard(
            @RequestParam("board_id") Long board_id) {

        OneBoardResponseDTO oneBoard = boardService.getOneBoard(board_id);
//        List<CommentResponseDTO> commentResponseDTOList = boardService.getCommentList(board_id);
//
//        oneBoard.setCommentResponseDTOList(commentResponseDTOList);
        return ResponseEntity.ok(oneBoard);
    }

    /**
     * 댓글 생성 매핑 함수
     * @param principalDetails
     * @param map
     * @return
     */
    @GetMapping("/api/create/comment")
    public ResponseEntity<?> createComment(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody Map<String, Object> map) {

        boardService.createComment(map, principalDetails);

        return ResponseEntity.ok("코멘트 작성 완료");
    }

    @GetMapping("api/delete/comment")
    public ResponseEntity<?> deleteComment(
            @RequestParam("comment_id") Long comment_id) {

        boardService.deleteComment(comment_id);


        return ResponseEntity.ok("코멘트 삭제 완료");
    }
}
