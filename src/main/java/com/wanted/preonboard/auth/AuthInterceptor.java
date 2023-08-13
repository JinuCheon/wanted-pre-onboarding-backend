package com.wanted.preonboard.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        String accessToken = request.getHeader(AUTHORIZATION_HEADER);

        if (accessToken == null && auth != null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "로그인이 필요합니다.");
            return false;
        }

        if (accessToken != null) {
            UserContext.CONTEXT.set(authService.validateAndGetUserId(accessToken));
        }

        log.info("유저 로그인 정보: {}", UserContext.CONTEXT.get());
        return true;
    }
}
}
