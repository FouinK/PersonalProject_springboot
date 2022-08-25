package com.example.PersonalProject.User;

import com.example.PersonalProject.DTO.MyPageResponseDTO;
import com.example.PersonalProject.Login.PrincipalDetails;
import com.example.PersonalProject.Login.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 회원가입 매핑 함수
     *
     * @param getUser 클라이언트로부터 입력 받은 회원가입 양식
     * @return
     */
    @PostMapping("/api/join")
    public ResponseEntity<?> join(@RequestBody Map<String, String> getUser) {
        userService.join(getUser);
        return ResponseEntity.ok("회원가입 완료");
    }

    /**
     * 회원 정보를 조회할 수 있는 매핑 함수
     * @param principalDetails
     * @return
     */
    @PostMapping("/api/mypage")
    public ResponseEntity<?> mypage(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        MyPageResponseDTO myPageResponseDTO = MyPageResponseDTO.builder()
                .username(principalDetails.getUserInfo().getUsername())
                .role(String.valueOf(principalDetails.getUserInfo().getRole()))
                .build();

        return ResponseEntity.ok(myPageResponseDTO);
    }

    /**
     * 쿠키 요청 응답 테스트
     * @param httpServletRequest
     * @param mySessionId
     * @return
     * @throws UnsupportedEncodingException
     */

    @GetMapping("/api/select_cookie")
    public ResponseEntity<?> select_cookie(
            HttpServletRequest httpServletRequest,
            @CookieValue(name = "mySessionId", required = false) String mySessionId) throws UnsupportedEncodingException {
        System.out.println("controller 쿠키 값 확인 : " + mySessionId);
        SessionManager sessionManager = new SessionManager();


        String cookie = (String) sessionManager.getSession(httpServletRequest);
        System.out.println("세션 매니저 객체 호출 후 getSession 메소드 활용 값 : " + cookie);
        return ResponseEntity.ok(mySessionId);
    }
}