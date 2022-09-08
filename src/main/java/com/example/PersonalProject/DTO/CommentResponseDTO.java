package com.example.PersonalProject.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDTO {
    private Long id;
    private String comment;

    private String writer;

    private LocalDateTime createdDate;

    @Builder
    public CommentResponseDTO(Long id, String comment, String writer, LocalDateTime createdDate) {
        this.id = id;
        this.comment = comment;
        this.writer = writer;
        this.createdDate = createdDate;
    }

    public CommentResponseDTO() {
    }
}
