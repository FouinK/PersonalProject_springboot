package com.example.PersonalProject.User;

import com.example.PersonalProject.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ThymeleafUserController {
    private final UserService userService;

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

    /**
     * 회원가입 매핑 함수
     * @param getUser
     * @return
     */
    @PostMapping("/thymeleaf/join")
    public ResponseEntity<?> join(
            @RequestBody Map<String, String> getUser) {
        System.out.println(getUser.toString());
        userService.join(getUser);
        return ResponseEntity.ok("회원가입 완료");
    }
}
