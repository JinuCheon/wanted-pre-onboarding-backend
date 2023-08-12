package com.wanted.preonboard.service.member.application.service;

import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberSignUpTest {

    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = new MemberRepository();
    }

    @Test
    void signUp() {
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
}
