package com.example.PersonalProject.Security;

import com.example.PersonalProject.DTO.PrincipalDetails;
import com.example.PersonalProject.Entity.UserInfo;
import com.example.PersonalProject.Security.SessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            System.out.println(2);
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println(principalDetails.getUserInfo().getUsername());
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
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        System.out.println("successfulAuthentication 실행 - 인증이 완료 됨 시도중");
        Cookie cookie = sessionManager.createSession("random",response);
        System.out.println("Cookie 값 확인 : " + cookie);
        response.addCookie(cookie);
    }
}