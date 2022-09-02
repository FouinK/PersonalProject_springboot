package com.example.PersonalProject.ThymeleafTest;

import com.example.PersonalProject.Login.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafMainController {

    /**
     * 타임리프 메인 화면
     * @param principalDetails
     * @return
     */
    @GetMapping("/")
    public String index(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails != null) {
            System.out.println("메인 페이지 유저네임 확인 : "+principalDetails.getUserInfo().getUsername());
        }else {
            System.out.println("유저정보 없음");
        }
        return "index";
    }

    /**
     * 타임리프 로그인 화면
     * @return
     */
    @GetMapping("/thymeleaf/login")
    public String login() {

        return "login";
    }
}
