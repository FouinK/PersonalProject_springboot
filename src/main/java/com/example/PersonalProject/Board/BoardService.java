package com.example.PersonalProject.Board;

import com.example.PersonalProject.DTO.AllBoradResponseDTO;
import com.example.PersonalProject.DTO.CreateBoardRequestDTO;
import com.example.PersonalProject.DTO.OneBoardResponseDTO;
import com.example.PersonalProject.Login.PrincipalDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    void createBoard(CreateBoardRequestDTO createBoardRequestDTO, PrincipalDetails principalDetails);

    Page<AllBoradResponseDTO> allBoard(Pageable pageable);

    OneBoardResponseDTO getOneBoard(Long team_id);

}
