package com.example.PersonalProject.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ThymeleafUserController {
    /**
     * 타임리프 로그인 화면
     * @return
     */
    @GetMapping("/thymeleaf/login")
    public String login() {

        return "User/login";
    }

    /**
     * 회원가입 페이지 이동 매핑 함수
     * @return
     */
    @GetMapping("/thymeleaf/joinPage")
    public String joinPage() {
        return "User/join";
    }

}
