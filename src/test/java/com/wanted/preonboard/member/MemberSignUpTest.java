package com.wanted.preonboard.member;

import com.wanted.preonboard.common.ApiTest;
import com.wanted.preonboard.common.Scenario;
import com.wanted.preonboard.member.domain.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class MemberSignUpTest extends ApiTest {

    @Autowired private MemberRepository memberRepository;

    @Test
    void signUp() {
        Scenario.signUpMember().request();
        assertThat(memberRepository.findAll()).hasSize(1);
    }

}
