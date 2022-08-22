package com.example.PersonalProject.Controller;

import com.example.PersonalProject.Entity.Role;
import com.example.PersonalProject.Entity.UserInfo;
import com.example.PersonalProject.Repository.UserRepository;
import com.example.PersonalProject.Security.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/api/join")
    public ResponseEntity<?> join(@RequestBody Map<String, String> getUser) {

        UserInfo userInfo = new UserInfo();

        userInfo.setUsername(getUser.get("username"));
        userInfo.setPassword(passwordEncoder.encode(getUser.get("password")));
        userInfo.setRole(Role.USER);
        userRepository.save(userInfo);

        return ResponseEntity.ok("회원가입 완료");
    }

    @GetMapping("/api/select_cookie")
    public ResponseEntity<?> select_cookie(
            HttpServletRequest httpServletRequest,
            @CookieValue(name = "mySessionId", required = false) String mySessionId) throws UnsupportedEncodingException {
        System.out.println("controller 쿠키 값 확인 : " + mySessionId);
        SessionManager sessionManager = new SessionManager();

        //필터 사용시 필요한 메서드
//        System.out.println("httpRequest.getCookies 값 확인 : " + Arrays.stream(httpServletRequest.getCookies()).findAny());
//        Cookie[] cookies = httpServletRequest.getCookies();
//        for (Cookie cookie : cookies) {
//            String str = URLDecoder.decode(cookie.getValue(), "UTF-8");
//            System.out.println("디코딩 값 : " + str);
//            System.out.println("겟 네임 값 :" + cookie.getName());
//        }

        String cookie = (String) sessionManager.getSession(httpServletRequest);
        System.out.println("세션 매니저 객체 호출 후 getSession 메소드 활용 값 : "+cookie);
        return ResponseEntity.ok(mySessionId);
    }
}

