package com.wanted.preonboard.member.domain;

import com.google.common.annotations.VisibleForTesting;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

@Getter
@Entity
@Table(name = "member")
@Comment("회원")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public Member(final String nickname, final String email, final String password) {
        validateConstructor(nickname, email, password);
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    private static void validateConstructor(final String nickname, final String email, final String password) {
        Assert.hasText(nickname, "nickname must not be empty");
        Assert.hasText(email, "email must not be empty");
        Assert.hasText(password, "password must not be empty");
    }

    @VisibleForTesting
    public Member(final Long id, final String nickname, final String email, final String password) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
