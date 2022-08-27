package com.example.PersonalProject.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 회원가입 메서드
     * @param getUser 컨트롤러에서 받은 회원가입 양식
     */
    @Override
    public void join(Map<String, String> getUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(getUser.get("username"));
        userInfo.setPassword(passwordEncoder.encode(getUser.get("password")));
        userInfo.setNickname(getUser.get("nickname"));
        userInfo.setRole(Role.USER);

        userRepository.save(userInfo);
    }
}
