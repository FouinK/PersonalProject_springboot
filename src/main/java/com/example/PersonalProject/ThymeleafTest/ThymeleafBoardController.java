package com.example.PersonalProject.ThymeleafTest;

import com.example.PersonalProject.Board.BoardService;
import com.example.PersonalProject.DTO.AllBoradResponseDTO;
import com.example.PersonalProject.DTO.CommentResponseDTO;
import com.example.PersonalProject.DTO.OneBoardResponseDTO;
import com.example.PersonalProject.Login.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ThymeleafBoardController {

    private final BoardService boardService;

    /**
     * 타임리프 게시판 화면
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/thymeleaf/board")
    public String getAllBoard(
            Model model,
            @PageableDefault(size = 10,sort = "viewCnt",direction = Sort.Direction.DESC) Pageable pageable) {

        Page<AllBoradResponseDTO> boardList = boardService.allBoard(pageable);
        model.addAttribute("boardList",boardList);
        return "board";
    }


    /**
     * 타임 리프 게시판 상세내용 화면
     * @param model
     * @param board_id
     * @return
     */
    @GetMapping("/thymeleaf/get_board")
    public String getBoard(
            Model model,
            @RequestParam("board_id") Long board_id) {

        OneBoardResponseDTO oneBoard = boardService.getOneBoard(board_id);
        List<CommentResponseDTO> commentResponseDTOList = boardService.getCommentList(board_id);
        oneBoard.setCommentResponseDTOList(commentResponseDTOList);
        model.addAttribute("board", oneBoard);

        return "boardDetail";
    }

    /**
     * 타임리프 코멘트 생성 매핑 함수
     * @param
     * @return
     */
    @PostMapping(value = "/thymeleaf/create/comment")
    public String createComment_thymeleaf(
            @RequestBody Map<String, Object> map,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(map);
        boardService.createComment(map, principalDetails);
        return "redirect:/";
    }
}