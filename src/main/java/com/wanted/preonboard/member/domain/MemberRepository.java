package com.wanted.preonboard.member.domain;

import java.util.HashMap;
import java.util.List;

public class MemberRepository {
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
