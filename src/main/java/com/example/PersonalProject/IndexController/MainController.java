package com.example.PersonalProject.IndexController;

import com.example.PersonalProject.Login.PrincipalDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

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

    @GetMapping("/api/react_test_login")
    public ResponseEntity<?> front_login_test(@RequestParam(name = "username") String username,
                                              @RequestParam(name = "password") String password) {
        System.out.println(username);
        System.out.println(password);
        Map<String, Object> map = new HashMap<>();
        map.put("success", "응답 성공");
        return ResponseEntity.ok(map);
    }
}
