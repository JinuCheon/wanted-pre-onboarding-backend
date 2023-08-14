package com.wanted.preonboard.post.domain;

import com.wanted.preonboard.member.domain.Member;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void isAuthor() {
        final Member member = new Member(1L, "email", "password", "name");
        final Post post = new Post(member, "title", "content");

        assertThat(post.isAuthor(member)).isTrue();
    }

    @Test
    void isAuthor_fail() {
        final Member member = new Member(1L, "email", "password", "name");
        final Member otherMember = new Member(2L, "otherEmail", "otherPassword", "otherName");
        final Post post = new Post(member, "title", "content");

        assertThat(post.isAuthor(otherMember)).isFalse();
    }

}