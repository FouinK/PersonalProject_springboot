package com.example.PersonalProject.Controller;

import com.example.PersonalProject.Entity.Role;
import com.example.PersonalProject.Entity.UserInfo;
import com.example.PersonalProject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody Map<String, String> getUser) {

        UserInfo userInfo = new UserInfo();

        userInfo.setUsername(getUser.get("username"));
        userInfo.setPassword(passwordEncoder.encode(getUser.get("password")));
        userInfo.setRole(Role.USER);
        userRepository.save(userInfo);

        return ResponseEntity.ok("회원가입 완료");
    }
}
