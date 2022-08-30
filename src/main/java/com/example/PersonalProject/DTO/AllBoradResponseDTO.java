package com.example.PersonalProject.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AllBoradResponseDTO {

    private Long board_id;

    private String title;

    private int viewCnt;

    private String writer;

    private LocalDateTime createdDate;

    @Builder
    public AllBoradResponseDTO(Long board_id, String title, int viewCnt, String writer, LocalDateTime createdDate) {
        this.board_id = board_id;
        this.title = title;
        this.viewCnt = viewCnt;
        this.writer = writer;
        this.createdDate = createdDate;
    }
}
