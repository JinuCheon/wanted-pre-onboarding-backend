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
        memberAdaptor = new MemberPort(memberRepository);
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
        private final HashMap<Long, Member> members = new HashMap<>();
        private Long sequence = 0L;
        public List<Member> findAll() {
            return List.copyOf(members.values());
        }

        public Member save(final Member member) {
            sequence++;
            members.put(sequence, member);
            return member;
        }
    }

    private class Member {
        private final String nickname;
        private final String email;
        private final String password;

        public Member(final String nickname, final String email, final String password) {
            this.nickname = nickname;
            this.email = email;
            this.password = password;
        }
    }

    public record MemberSignUpRequest(
            String nickname,
            String email,
            String password
    ) {
        public MemberSignUpRequest {
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

    private interface MemberAdaptor {
        void signUp(MemberSignUpRequest request);
    }

    private class MemberPort implements MemberAdaptor {
        private final MemberRepository memberRepository;

        public MemberPort(final MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        @Override
        public void signUp(final MemberSignUpRequest request) {
            final String encryptPassword = encryptPasswordByHash256(request.password);
            final Member member = new Member(request.nickname, request.email, encryptPassword);
            memberRepository.save(member);
        }

        private String encryptPasswordByHash256(final String password) {
            return password;
        }
    }
}
