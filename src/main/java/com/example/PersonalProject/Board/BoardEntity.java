package com.example.PersonalProject.Board;

import com.example.PersonalProject.User.UserInfoEntity;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Data
public class BoardEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private UserInfoEntity userInfoEntity;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int viewCnt;

    @Builder
    public BoardEntity(Long id, String title, UserInfoEntity userInfoEntity, String content, int viewCnt) {
        this.id = id;
        this.title = title;
        this.userInfoEntity = userInfoEntity;
        this.content = content;
        this.viewCnt = viewCnt;
    }
}
