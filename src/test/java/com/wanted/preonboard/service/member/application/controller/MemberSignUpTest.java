package com.wanted.preonboard.service.member.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberSignUpTest {

    private MemberRepository memberRepository;
    private MemberAdaptor memberAdaptor;

    @BeforeEach
    void setUp() {
        memberRepository = new MemberRepository();
    }

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
        memberAdaptor.signUp(request);

        assertThat(memberRepository.findAll()).hasSize(1);
    }

    private class MemberRepository {
        private final HashMap<Long, Member> member = new HashMap<>();
        private Long sequence = 0L;
        public List<Member> findAll() {
            return List.copyOf(member.values());
        }
    }

    private class Member {
    }

    public record MemberSignUpRequest(String nickname, String email, String password) {
        public MemberSignUpRequest {
            validateConstructor();
        }

        private void validateConstructor() {
            Assert.hasText(nickname, "닉네임은 필수입니다.");
            Assert.hasText(email, "이메일은 필수입니다.");
            if (!email.contains("@")) {
                throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
            }
            Assert.hasText(password, "비밀번호는 필수입니다.");
            if (password.length() < 8) {
                throw new IllegalArgumentException("비밀번호는 8자리 이상이어야 합니다.");
            }
        }
    }
            this.memberRepository = memberRepository;
}
