package com.wanted.preonboard.member.application;

import com.wanted.preonboard.config.BcryptEncoder;
import com.wanted.preonboard.member.domain.Member;
import com.wanted.preonboard.member.domain.MemberRepository;
import com.wanted.preonboard.member.dto.request.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BcryptEncoder bcryptEncoder;

    public void signUp(final MemberSignUpRequest request) {
        final String encryptPassword = encryptPasswordByHash256(request.password());
        final Member member = new Member(request.nickname(), request.email(), encryptPassword);
        memberRepository.save(member);
    }

    private String encryptPasswordByHash256(final String originalPassword) {
        return bcryptEncoder.hashPassword(originalPassword);
    }
}
