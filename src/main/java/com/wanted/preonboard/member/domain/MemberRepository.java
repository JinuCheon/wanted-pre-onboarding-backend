package com.wanted.preonboard.member.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class MemberRepository {
    private static final HashMap<Long, Member> members = new HashMap<>();
    private static Long sequence = 0L;

    public List<Member> findAll() {
        return List.copyOf(members.values());
    }

    public Member save(final Member member) {
        members.put(sequence++, member);
        return member;
    }
}
