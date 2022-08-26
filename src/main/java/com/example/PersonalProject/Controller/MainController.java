package com.example.PersonalProject.Controller;

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

    @GetMapping("/api/react_test_login")
    public ResponseEntity<?> front_login_test(@RequestParam(name = "username") String username,
                                              @RequestParam(name = "password") String password) {
        System.out.println(username);
        System.out.println(password);
        Map<String, Object> map = new HashMap<>();
        map.put("success", "응답 성공");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails != null) {
            System.out.println("메인 페이지 유저네임 확인 : "+principalDetails.getUserInfo().getUsername());
        }else {
            System.out.println("유저정보 없음");
        }
        return "index";
    }

    //@Authentication 잘 사라졌는지 확인 필요
    @GetMapping("/login")
    public ResponseEntity<?> login(){
        Map<String, Object> map = new HashMap<>();
        System.out.println("로그아웃 성공");
        map.put("logout",true);
        return ResponseEntity.ok(map);
    }
}
