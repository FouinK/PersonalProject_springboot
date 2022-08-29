package com.example.PersonalProject.DTO;

import lombok.Data;

@Data
public class CreateBoardRequestDTO {
    private String title;

    private String content;

    private String writer;
}
