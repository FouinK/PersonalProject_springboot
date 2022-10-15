package com.example.PersonalProject.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 회원가입 메서드
     *
     * @param getUser 컨트롤러에서 받은 회원가입 양식
     */
    @Override
    public boolean join(Map<String, String> getUser) {

        boolean duplication = userRepository.existsByUsername(getUser.get("username"));

        if (duplication == true) {

            return true;
        } else {

            UserInfoEntity userInfo = new UserInfoEntity();
            userInfo.setUsername(getUser.get("username"));
            userInfo.setPassword(passwordEncoder.encode(getUser.get("password")));
            userInfo.setNickname(getUser.get("nickname"));
            userInfo.setRole(Role.USER);
            userRepository.save(userInfo);
            return false;
        }
    }
}
