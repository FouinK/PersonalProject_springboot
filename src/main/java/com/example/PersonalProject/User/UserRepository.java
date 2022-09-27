package com.example.PersonalProject.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfoEntity,Long> {

    UserInfoEntity findByUsername(String username);

    Optional<UserInfoEntity> findById(Long userId);

    boolean existsByUsername(String username);
}
