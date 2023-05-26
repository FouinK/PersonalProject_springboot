package com.example.PersonalProject.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseMemberDtd {
    private String memberId;
    private String username;

}
