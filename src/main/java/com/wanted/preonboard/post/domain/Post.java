package com.wanted.preonboard.post.domain;

import com.wanted.preonboard.member.domain.Member;
import org.springframework.util.Assert;

public class Post {
    private final Member member;
    private final String title;
    private final String content;

    public Post(final Member member, final String title, final String content) {
        validateConstructor(member, title, content);
        this.member = member;
        this.title = title;
        this.content = content;
    }

    private static void validateConstructor(final Member member, final String title, final String content) {
        Assert.notNull(member, "member must not be null");
        Assert.hasText(title, "title must not be empty");
        Assert.hasText(content, "content must not be empty");
    }
}
