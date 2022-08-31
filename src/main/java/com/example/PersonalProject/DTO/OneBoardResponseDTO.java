package com.example.PersonalProject.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class OneBoardResponseDTO {

    private Long board_id;

    private String title;

    private String content;

    private String writer;

    private int viewCnt;

    private LocalDateTime createdDate;

    @Builder
    public OneBoardResponseDTO(Long board_id, String title, String content, String writer, int viewCnt, LocalDateTime createdDate) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCnt = viewCnt;
        this.createdDate = createdDate;
    }
}
