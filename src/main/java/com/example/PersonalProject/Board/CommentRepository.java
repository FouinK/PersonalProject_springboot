package com.example.PersonalProject.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("select DISTINCT b from CommentEntity b join fetch b.boardEntity ")
    List<CommentEntity> findByBoardEntity_Id(Long board_id);
}
