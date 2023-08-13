package com.wanted.preonboard.member.dto.request;

import org.springframework.util.Assert;

public record MemberSignInRequest(String email, String password) {
    public MemberSignInRequest {
        Assert.hasText(email, "이메일은 필수입니다.");
        Assert.hasText(password, "비밀번호는 필수입니다.");
    }
}
