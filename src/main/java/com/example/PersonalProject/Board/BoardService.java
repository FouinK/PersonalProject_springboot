package com.example.PersonalProject.Board;

import com.example.PersonalProject.DTO.AllBoradResponseDTO;
import com.example.PersonalProject.DTO.CreateBoardRequestDTO;
import com.example.PersonalProject.Login.PrincipalDetails;

import java.util.List;

public interface BoardService {
    void createBoard(CreateBoardRequestDTO createBoardRequestDTO, PrincipalDetails principalDetails);

    List<AllBoradResponseDTO> allBoard();

}
