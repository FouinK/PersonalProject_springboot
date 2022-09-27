package com.example.PersonalProject.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @EntityGraph(attributePaths = {"userInfoEntity","commentEntity"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<BoardEntity> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"userInfoEntity","commentEntity"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<BoardEntity> findById(Long id);

}
