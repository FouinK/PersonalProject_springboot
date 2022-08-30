package com.example.PersonalProject.User;

import com.example.PersonalProject.Board.BoardEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@Getter
public class UserInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;
//
//    @OneToMany(mappedBy = "userInfoEntity",fetch = FetchType.EAGER)
//    private Set<BoardEntity> productUploadFiles = new LinkedHashSet<>();

    @Builder
    public UserInfoEntity(Long id, String username, String password, Role role, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
    }

    public UserInfoEntity() {

    }
}
