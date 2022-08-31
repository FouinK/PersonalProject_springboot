package com.example.PersonalProject.ThymeleafTest;

import com.example.PersonalProject.Board.BoardEntity;
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

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {

    private final BoardService boardService;

    /**
     * 타임리프 게시판 화면
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/thmyleaf/board")
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
    @GetMapping("/thmyleaf/get_board")
    public String getBoard(
            Model model,
            @RequestParam("team_id") Long team_id) {

        OneBoardResponseDTO oneBoard = boardService.getOneBoard(team_id);

        model.addAttribute("board", oneBoard);

        return "boardDetail";
    }
}
