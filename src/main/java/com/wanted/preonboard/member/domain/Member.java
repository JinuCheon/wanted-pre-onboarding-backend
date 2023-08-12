package com.wanted.preonboard.member.domain;

public class Member {
    private final String nickname;
    private final String email;
    private final String password;

    public Member(final String nickname, final String email, final String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
