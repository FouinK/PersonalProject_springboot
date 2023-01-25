package com.example.PersonalProject.Login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LogoutHandler implements LogoutSuccessHandler {

    private final SessionManager sessionManager;
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        sessionManager.expire(request);
        System.out.println("로그아웃 석세스 헨들러 실행");

        response.getWriter().write("서버로 부터 로그아웃 메세지");
    }
}
