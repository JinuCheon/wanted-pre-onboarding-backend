package com.wanted.preonboard.post.domain;

import com.wanted.preonboard.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Getter
@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

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

    public boolean isAuthor(final Member member) {
        return this.member.equals(member);
    }

    public void update(final String updatedTitle, final String updatedContent) {
        validateUpdate(updatedTitle, updatedContent);
        this.title = updatedTitle;
        this.content = updatedContent;
    }

    private void validateUpdate(final String updatedTitle, final String updatedContent) {
        Assert.hasText(updatedTitle, "title must not be empty");
        Assert.hasText(updatedContent, "content must not be empty");
    }
}
