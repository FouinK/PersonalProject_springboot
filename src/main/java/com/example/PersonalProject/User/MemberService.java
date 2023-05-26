package com.example.PersonalProject.User;

import com.example.PersonalProject.DTO.ResponseMemberDtd;
import com.example.PersonalProject.model.Member;
import com.example.PersonalProject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public ResponseMemberDtd getMember(String memberId) {

        Member mem = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("fdfasd"));


        return ResponseMemberDtd.builder()
                .memberId(mem.getMemberId())
                .username(mem.getUsername())
                .build();
    }
}
