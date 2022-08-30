package com.example.PersonalProject.Filter;

import com.example.PersonalProject.Login.PrincipalDetails;
import com.example.PersonalProject.Login.SessionManager;
import com.example.PersonalProject.User.UserInfoEntity;
import com.example.PersonalProject.User.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class SessionAuthorizationFilter extends BasicAuthenticationFilter {
    private final SessionManager sessionManager;
    private final UserRepository userRepository;
    public SessionAuthorizationFilter(AuthenticationManager authenticationManager, SessionManager sessionManager, UserRepository userRepository) {
        super(authenticationManager);
        this.sessionManager = sessionManager;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("실행하자마자 동작하는 필터 작동 됨");
        Cookie[] cookies = request.getCookies();
        String userID = null;

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("mySessionId")) {

                    userID = (String) sessionManager.getSession(request);
//                    System.out.println("필터로 확인 한 유저인포 값 : " + userID);
                } else {

//                    System.out.println("mySessionId 없음");
                }
            }
        }

        if (userID != null) {
            Optional<UserInfoEntity> userInfo = userRepository.findById(Long.valueOf(userID));

            if (userInfo.isPresent()) {
                PrincipalDetails principalDetails = new PrincipalDetails(userInfo.get());
                Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}