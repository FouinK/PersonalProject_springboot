package com.example.PersonalProject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private String memberId;

    @Column
    private String username;


    private String password;
}
