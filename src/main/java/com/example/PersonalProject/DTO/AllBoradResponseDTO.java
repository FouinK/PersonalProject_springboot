package com.example.PersonalProject.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AllBoradResponseDTO {

    private String title;

    private int viewCnt;

    private String writer;

    @Builder
    public AllBoradResponseDTO(String title, int viewCnt, String writer) {
        this.title = title;
        this.viewCnt = viewCnt;
        this.writer = writer;
    }
}
