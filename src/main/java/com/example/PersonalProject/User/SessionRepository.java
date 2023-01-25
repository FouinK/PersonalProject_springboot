package com.example.PersonalProject.User;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionRepository extends CrudRepository<SessionEntity, String> {

    @Override
    Optional<SessionEntity> findById(String sessionId);

}
