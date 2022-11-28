package com.example.PersonalProject.Board;

import com.example.PersonalProject.User.Role;
import com.example.PersonalProject.User.UserInfoEntity;
import com.example.PersonalProject.User.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardEntityTest {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("LAZY 또는 Eager로 끌어오기")
    public void getLazyAndEager() {
        // given
        UserInfoEntity userInfo = UserInfoEntity
                .builder()
                .username("username")
                .password("pas")
                .nickname("nic")
                .role(Role.USER)
                .build();

        BoardEntity boardEntity = BoardEntity
                .builder()
                .title("타이틀")
                .content("내용")
                .viewCnt(0)
                .userInfoEntity(userInfo)
                .build();
        userRepository.save(userInfo);
        boardRepository.save(boardEntity);
        em.flush();
        em.clear();
        // when
        Optional<BoardEntity> findBoard = boardRepository.findById(boardEntity.getId());
        findBoard.get();

        // then

    }

}