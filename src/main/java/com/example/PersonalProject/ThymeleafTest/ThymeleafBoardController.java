package com.example.PersonalProject.ThymeleafTest;

import com.example.PersonalProject.Board.BoardService;
import com.example.PersonalProject.DTO.AllBoradResponseDTO;
import com.example.PersonalProject.DTO.OneBoardResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
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
     * @param team_id
     * @return
     */
    @GetMapping("/thymeleaf/get_board")
    public String getBoard(
            Model model,
            @RequestParam("team_id") Long team_id) {

        OneBoardResponseDTO oneBoard = boardService.getOneBoard(team_id);

        model.addAttribute("board", oneBoard);

        return "boardDetail";
    }

    /**
     * 타임리프 코멘트 생성 매핑 함수
     * @param comment
     * @return
     */
    @GetMapping("/thymeleaf/create/comment")
    public String createComment(
            String comment
            /*@AuthenticationPrincipal PrincipalDetails principalDetails*/) {
        System.out.println("컨트롤러 코멘트 값"+comment);
        Map<String, Object> map = new HashMap<>();
        map.put("comment", comment);
//        boardService.createComment(map, principalDetails);
        return "redirect:/";
    }
}
