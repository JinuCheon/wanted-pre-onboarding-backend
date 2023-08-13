package com.wanted.preonboard.post;

import com.wanted.preonboard.member.domain.Member;

public class Post {
    private final Member member;
    private final String title;
    private final String content;

    public Post(final Member member, final String title, final String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }
}
