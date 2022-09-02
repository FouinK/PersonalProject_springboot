package com.example.PersonalProject.ThymeleafTest;

import org.springframework.http.ResponseEntity;
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


}
