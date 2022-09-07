package com.example.PersonalProject.Board;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class CommentEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String comment;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private BoardEntity boardEntity;

    @Builder
    public CommentEntity(Long id, String writer, String comment, BoardEntity boardEntity) {
        this.id = id;
        this.writer = writer;
        this.comment = comment;
        this.boardEntity = boardEntity;
    }
}