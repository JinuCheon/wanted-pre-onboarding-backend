package com.wanted.preonboard.member.dto.request;

import org.springframework.util.Assert;

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
