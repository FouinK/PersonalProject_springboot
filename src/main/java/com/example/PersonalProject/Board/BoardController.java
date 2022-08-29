package com.example.PersonalProject.Board;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    @PostMapping
    public ResponseEntity<?> createBoard() {

        return ResponseEntity.ok("게시판 작성 완료");
    }
}
