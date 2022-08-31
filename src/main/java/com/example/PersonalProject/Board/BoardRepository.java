package com.example.PersonalProject.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Page<BoardEntity> findAll(Pageable pageable);

    Optional<BoardEntity> findById(Long id);
}
