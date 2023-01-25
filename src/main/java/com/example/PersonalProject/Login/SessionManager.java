package com.example.PersonalProject.Login;

import com.example.PersonalProject.User.SessionEntity;
import com.example.PersonalProject.User.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 세션 관리
 */
@Component
@RequiredArgsConstructor
public class SessionManager {
    private final SessionRepository sessionRepository;
    private static final String SESSION_COOKIE_NAME = "mySessionId";
    private static final Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * 세션 생성
     */
    public Cookie createSession(Object value){
        // 세션 id를 생성하고, 값을 세션에 저장
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        // 쿠키 생성
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        mySessionCookie.setMaxAge(10);                //1800초 설정
        mySessionCookie.setPath("/");                   //모든 경로에서 접근 가능 하도록 설정

        SessionEntity sessionEntity = new SessionEntity(sessionId, (String) value, 10);

        sessionRepository.save(sessionEntity);

        return mySessionCookie;
    }

    /**
     * 세션 조회
     */
    public Object getSession(HttpServletRequest request){

        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie == null){
            return null;
        }

        System.out.println("sessionCookie.getValue() = " + sessionCookie.getValue());
        Optional<SessionEntity> findSession = sessionRepository.findById(sessionCookie.getValue());

        if (!findSession.isPresent()) {
            return null;
        }

        System.out.println(findSession.get().getCustId());

        return findSession.get().getCustId();

//        return findSession.<Object>map(SessionEntity::getCustId).orElse(null);

        //        return sessionStore.get(sessionCookie.getValue());
    }

    /**
     * 세션 만료
     */
    public void expire(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie != null){
            sessionStore.remove(sessionCookie.getValue());
        }
    }

    private Cookie findCookie(HttpServletRequest request, String cookieName) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }
}