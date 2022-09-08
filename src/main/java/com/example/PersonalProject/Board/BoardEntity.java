package com.example.PersonalProject.Board;

import com.example.PersonalProject.User.UserInfoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
public class BoardEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserInfoEntity userInfoEntity;

    @OneToMany(mappedBy = "boardEntity", fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntity;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int viewCnt;

    @Builder
    public BoardEntity(Long id, String title, UserInfoEntity userInfoEntity, List<CommentEntity> commentEntity, String content, int viewCnt) {
        this.id = id;
        this.title = title;
        this.userInfoEntity = userInfoEntity;
        this.commentEntity = commentEntity;
        this.content = content;
        this.viewCnt = viewCnt;
    }
}
