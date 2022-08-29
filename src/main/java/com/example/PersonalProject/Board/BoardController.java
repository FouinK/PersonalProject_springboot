package com.example.PersonalProject.Board;

import com.example.PersonalProject.DTO.CreateBoardRequestDTO;
import com.example.PersonalProject.Login.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/create_board")
    public ResponseEntity<?> createBoard(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody CreateBoardRequestDTO createBoardRequestDTO) {
        boardService.createBoard(createBoardRequestDTO, principalDetails);
        return ResponseEntity.ok("게시판 작성 완료");
    }
}
