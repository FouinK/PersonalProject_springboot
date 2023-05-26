package com.example.PersonalProject.repository;

import com.example.PersonalProject.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
