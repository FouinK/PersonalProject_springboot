package com.example.PersonalProject.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Test
    void 회원가입_테스트() {
        //given
        Map<String, String> getUser = new HashMap<>();

        getUser.put("username", "spring");
        getUser.put("password", passwordEncoder.encode("spring"));
        getUser.put("nickname", "spring_nickname");

        boolean test_boolean = userService.join(getUser);

        Assertions.assertEquals(false, test_boolean);

    }

    @Test
    void 중복회원_테스트() {
        Map<String, String> getUser = new HashMap<>();

        getUser.put("username", "spring");
        getUser.put("password", passwordEncoder.encode("spring"));
        getUser.put("nickname", "spring_nickname");

        boolean test_boolean = userService.join(getUser);

        Assertions.assertEquals(true, test_boolean);

    }



}