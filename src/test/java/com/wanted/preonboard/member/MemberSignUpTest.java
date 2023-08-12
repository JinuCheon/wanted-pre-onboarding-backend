package com.wanted.preonboard.member;

import com.wanted.preonboard.member.application.MemberService;
import com.wanted.preonboard.member.domain.MemberRepository;
import com.wanted.preonboard.member.dto.request.MemberSignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberSignUpTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;

    @Test
    void signUp() {
        final String nickname = "nickname";
        final String email = "woojin8787@gmail.com";
        final String password = "password1234";

        final MemberSignUpRequest request = new MemberSignUpRequest(
                nickname,
                email,
                password
        );
        memberService.signUp(request);

        assertThat(memberRepository.findAll()).hasSize(1);
    }

}
