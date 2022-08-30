package com.example.PersonalProject.Login;

import com.example.PersonalProject.User.UserInfoEntity;
import com.example.PersonalProject.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfoEntity user = userRepository.findByUsername(username);
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        return principalDetails;

    }
}
