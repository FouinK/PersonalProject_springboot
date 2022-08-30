package com.example.PersonalProject.Board;

import com.example.PersonalProject.DTO.AllBoradResponseDTO;
import com.example.PersonalProject.DTO.CreateBoardRequestDTO;
import com.example.PersonalProject.Login.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시판 작성 함수
     * @param principalDetails
     * @param createBoardRequestDTO
     * @return
     */
    @PostMapping("/api/create_board")
    public ResponseEntity<?> createBoard(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody CreateBoardRequestDTO createBoardRequestDTO) {
        boardService.createBoard(createBoardRequestDTO, principalDetails);
        return ResponseEntity.ok("게시판 작성 완료");
    }

    /**
     * 전체 게시판 응답 매핑 함수
     * @return
     */
    @GetMapping("/api/board")
    public ResponseEntity<?> getAllBoard() {

        List<AllBoradResponseDTO> allBoradResponseDTOList = boardService.allBoard();

        return ResponseEntity.ok(allBoradResponseDTOList);

    }
}
