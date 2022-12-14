package com.example.PersonalProject.Board;

import com.example.PersonalProject.Board.BoardService;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ThymeleafBoardController {

    private final BoardService boardService;

    /**
     * 타임리프 게시판 화면
     *
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/thymeleaf/board")
    public String getAllBoard(
            Model model,
            @PageableDefault(size = 10, sort = "viewCnt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<AllBoradResponseDTO> boardList = boardService.allBoard(pageable);
        model.addAttribute("boardList", boardList);
        return "Board/board";
    }

    /**
     * 게시글 작성화면으로 이동 하는 매핑 함수
     * @return
     */
    @GetMapping("/thymeleaf/create/boardPage")
    public String createBoardPage() {
        return "/Board/createBoardPage";
    }

    /**
     * 게시글 작성 함수
     * @param createBoardRequestDTO
     * @param principalDetails
     * @return
     */
    @PostMapping("/thymeleaf/create/board")
    public ResponseEntity<?> createBoard(
            @RequestBody CreateBoardRequestDTO createBoardRequestDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        boardService.createBoard(createBoardRequestDTO, principalDetails);
        return ResponseEntity.ok("게시글 작성 완료");
    }

    /**
     * 타임 리프 게시판 상세내용 화면
     *
     * @param model
     * @param board_id
     * @return
     */
    @GetMapping("/thymeleaf/get_board")
    public String getBoard(
            Model model,
            @RequestParam("board_id") Long board_id) {

        OneBoardResponseDTO oneBoard = boardService.getOneBoard(board_id);
        model.addAttribute("board", oneBoard);

        return "Board/boardDetail";
    }

    /**
     * 타임리프 코멘트 생성 매핑 함수
     *
     * @param
     * @return
     */
    @PostMapping("/thymeleaf/create/comment")
    public ResponseEntity<?> createComment_thymeleaf(
            @RequestBody Map<String, Object> map,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(map);
        boardService.createComment(map, principalDetails);
        return ResponseEntity.ok("생성 완료");
    }


    /**
     * 댓글 삭제 매핑 함수
     *
     * @param comment_id
     * @return
     */
    @GetMapping("thymeleaf/delete/comment")
    public ResponseEntity<?> deleteComment(
            @RequestParam(value = "comment_id", required = false) Long comment_id) {
        boardService.deleteComment(comment_id);
        return ResponseEntity.ok("삭제 완료");
    }

    /**
     * 댓글 수정 매핑 함수
     *
     * @param map
     * @return
     */
    @PostMapping("thymeleaf/update/comment")
    public ResponseEntity<?> updateComment(
            @RequestBody Map<String, Object> map,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        boardService.updateComment(map, principalDetails);
        return ResponseEntity.ok("업데이트 완료");
    }
}