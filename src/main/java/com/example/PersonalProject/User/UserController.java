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
    private final SessionManager sessionManager;
    private final SessionRepository sessionRepository;
    /**
     * 회원가입 매핑 함수
     *
     * @param getUser 클라이언트로부터 입력 받은 회원가입 양식
     * @return
     */
    @PostMapping("/api/join")
    public ResponseEntity<?> join(@RequestBody Map<String, String> getUser) {
        boolean duplication = userService.join(getUser);
        if (duplication == true) {
            return ResponseEntity.ok("회원가입 실패");
        } else {
            return ResponseEntity.ok("회원가입 완료");
        }
    }

    /**
     * 회원 정보를 조회할 수 있는 매핑 함수
     *
     * @param principalDetails
     * @return
     */
    @GetMapping("/api/mypage")
    public ResponseEntity<?> mypage(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        MyPageResponseDTO myPageResponseDTO = MyPageResponseDTO.builder()
                .username(principalDetails.getUserInfo().getUsername())
                .role(String.valueOf(principalDetails.getUserInfo().getRole()))
                .nickname(String.valueOf(principalDetails.getUserInfo().getNickname()))
                .build();

        return ResponseEntity.ok(myPageResponseDTO);
    }

    /**
     * 쿠키 요청 응답 테스트
     *
     * @param httpServletRequest
     * @param mySessionId
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/api/select_cookie")
    public ResponseEntity<?> select_cookie(
            HttpServletRequest httpServletRequest,
            @CookieValue(name = "mySessionId", required = false) String mySessionId) {
        System.out.println("controller 쿠키 값 확인 : " + mySessionId);


        String cookie = (String) sessionManager.getSession(httpServletRequest);
        System.out.println("세션 매니저 객체 호출 후 getSession 메소드 활용 값 : " + cookie);
        return ResponseEntity.ok(mySessionId);
    }

    /**
     * 세션 만료 테스트 매핑 함수
     *
     * @param principalDetails
     * @return
     */
    @GetMapping("/api/where")
    public ResponseEntity<?> where(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(principalDetails.toString());
        return ResponseEntity.ok("작동 되면 안되는 구문");
    }

    @GetMapping("/getCache")
    public Object getCache() {
        return sessionRepository.findById("34fa4ec8-adc4-4dcb-90fe-2cd2f5594175");
    }

}