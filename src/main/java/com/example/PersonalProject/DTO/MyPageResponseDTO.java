package com.example.PersonalProject.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class MyPageResponseDTO {
    private String username;
    private String role;
    private String nickname;

    @Builder
    public MyPageResponseDTO(String username, String role, String nickname) {
        this.username = username;
        this.role = role;
        this.nickname = nickname;
    }
}
