package com.wanted.preonboard.auth;

import com.wanted.preonboard.member.dto.response.SignInResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthService {

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.expiration-hours}")
    private int expirationHours;

    public SignInResponse createToken(Long memberId) {
        final String token = generateToken(memberId);
        return new SignInResponse(memberId, token);
    }

    private String generateToken(final Long memberId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofHours(expirationHours).toMillis());
        Map<String, String> payloads = new HashMap<>();
        payloads.put("memberId", String.valueOf(memberId));

        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setClaims(payloads)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
            .compact();
    }

    public Long validateJwt(final String accessToken) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
            return Long.valueOf((String) claims.get("memberId"));
        } catch (ExpiredJwtException e) {
            log.info("JwtTokenServiceImpl|Token expired: {}", accessToken, e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.");
        } catch (Exception e) {
            log.warn("JwtTokenServiceImpl|Token Exception: {}", accessToken, e);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다.");
        }
    }

}
