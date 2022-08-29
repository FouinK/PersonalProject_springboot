package com.example.PersonalProject.Board;

import com.example.PersonalProject.DTO.CreateBoardRequestDTO;
import com.example.PersonalProject.Login.PrincipalDetails;

public interface BoardService {
    void createBoard(CreateBoardRequestDTO createBoardRequestDTO, PrincipalDetails principalDetails);
}
