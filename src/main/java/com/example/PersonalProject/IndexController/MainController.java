package com.example.PersonalProject.IndexController;

import com.example.PersonalProject.DTO.CreateMemberDto;
import com.example.PersonalProject.DTO.ResponseMemberDtd;
import com.example.PersonalProject.Login.PrincipalDetails;
import com.example.PersonalProject.User.MemberService;
import com.example.PersonalProject.model.Member;
import com.example.PersonalProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;


    /**
     * 타임리프 메인 화면
     *
     * @param principalDetails
     * @return
     */
    @GetMapping("/")
    public String index(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//        throw new MyException("메인 페이지에서 예외처리 해보기");
        if (principalDetails != null) {
            System.out.println("메인 페이지 유저네임 확인 : " + principalDetails.getUserInfo().getUsername());
        } else {
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

    @GetMapping("/api/arrayRequest/test")
    public ResponseEntity<?> api_arrayRequest_test(@RequestParam(name = "name") String[] name) {
        for (String s : name) {
            System.out.println("받은 값 : " + s);

        }
        return ResponseEntity.ok(name);
    }

    @PostMapping("/member")
    public ResponseEntity<?> createMember(
            @RequestBody CreateMemberDto createMemberDto) {
        memberRepository.save(Member.builder()
                .memberId("123123")
                .username(createMemberDto.getUsername())
                .password(createMemberDto.getPassword())
                .build());
        return ResponseEntity.ok("");
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> getMember(
            @PathVariable(name = "memberId") String memberId) {
        return ResponseEntity.ok(
                memberService.getMember(memberId)
        );
    }

}
