package com.example.PersonalProject.User;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("session")
@Getter
@Setter
public class SessionEntity {
    @Id
    private String sessionId;

    private String custId;

    @TimeToLive
    Integer expiration;

    public SessionEntity(String sessionId, String custId, Integer expiration) {
        this.sessionId = sessionId;
        this.custId = custId;
        this.expiration = expiration;
    }

}
