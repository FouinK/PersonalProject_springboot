package com.example.PersonalProject.Filter;

import com.example.PersonalProject.Login.PrincipalDetails;
import com.example.PersonalProject.Login.SessionManager;
import com.example.PersonalProject.User.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class SessionAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final SessionManager sessionManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("SessionAuthenticationFilter 실행 - 로그인 시도중");
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo user = null;
        try {
            user = objectMapper.readValue(request.getInputStream(), UserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        try {
            authenticationManager.authenticate(authenticationToken);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            System.out.println("authentication : "+authentication);
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("principalDetails getUsername() : "+principalDetails.getUserInfo().getUsername());
            return authentication;
        } catch (NullPointerException e) {
            try {
                response.sendError(401);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        System.out.println("successfulAuthentication 실행 - 인증이 완료 됨 시도중");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        System.out.println("세션 생성 필터 principalDetails의 유저 아이디 값 : "+principalDetails.getUserInfo().getId());
        Cookie cookie = sessionManager.createSession(String.valueOf(principalDetails.getUserInfo().getId()));
        System.out.println("Cookie 값 확인 : " + cookie.getValue());
        response.addHeader("Access-Control-Allow-Origin","http://localhost:3000");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("login response success");
        response.addCookie(cookie);

    }
}