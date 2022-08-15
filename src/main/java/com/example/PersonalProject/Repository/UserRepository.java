package com.example.PersonalProject.Repository;

import com.example.PersonalProject.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo,Long> {

    UserInfo findByUsername(String username);

}
