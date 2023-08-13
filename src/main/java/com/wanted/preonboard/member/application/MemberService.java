package com.wanted.preonboard.member.application;

import com.wanted.preonboard.config.BcryptEncoder;
import com.wanted.preonboard.member.domain.Member;
import com.wanted.preonboard.member.domain.MemberRepository;
import com.wanted.preonboard.member.dto.request.MemberSignInRequest;
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

    public Long signIn(final MemberSignInRequest request) {
        final Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));
        validatePassword(request.password(), member.getPassword());
        return member.getId();
    }

    private String encryptPasswordByHash256(final String originalPassword) {
        return bcryptEncoder.hashPassword(originalPassword);
    }

    private void validatePassword(final String originalPassword, final String encryptPassword) {
        if(!bcryptEncoder.isMatch(originalPassword, encryptPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
