package com.example.PersonalProject.Security;

import com.example.PersonalProject.DTO.PrincipalDetails;
import com.example.PersonalProject.Entity.UserInfo;
import com.example.PersonalProject.Repository.UserRepository;
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
        UserInfo user = userRepository.findByUsername(username);
        System.out.println("user : "+user);
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        System.out.println("principalDetails : "+principalDetails);
        return principalDetails;

    }
}