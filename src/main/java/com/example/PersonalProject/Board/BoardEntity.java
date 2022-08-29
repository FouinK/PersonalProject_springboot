package com.example.PersonalProject.Board;

import com.example.PersonalProject.User.UserInfoEntity;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class BoardEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private UserInfoEntity userInfoEntity;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int viewCnt;

}
