package com.sparta.lunchrecommender.global.security;

import com.sparta.lunchrecommender.domain.user.constant.Token;
import com.sparta.lunchrecommender.global.util.UserUtil;
import com.sparta.lunchrecommender.global.util.JwtUtil;
import com.sparta.lunchrecommender.domain.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "JWT 검증 및 인가")
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserUtil userUtil;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("In JwtAuthorizationFilter");

        String tokenValue = jwtUtil.getJwtFromHeader(httpServletRequest, Token.AUTHORIZATION_HEADER);
        log.info(httpServletRequest.getRequestURL().toString());
        if (
                StringUtils.hasText(tokenValue)
                        && !httpServletRequest.getRequestURL().toString().contains("/api/auth/")
                        && !httpServletRequest.getRequestURL().toString().contains("/api/login/")
                        && !httpServletRequest.getRequestURL().toString().contains("/api/user/signup")
        ) {
            if (!jwtUtil.validateToken(tokenValue)) {
                log.error("Token Error");
                return;
            }
            Claims info = jwtUtil.getUserInfoFromToken(tokenValue);
            if(userUtil.userVerifyByLoginId(info.getSubject()).getRefresh_token() == null) {
                log.warn("Refresh Token is Null but try Authorization, 로그인을 먼저 해야합니다");
                throw new IllegalAccessError("로그인 해주세요");
            }
            try {
                log.info("인가 설정");
                setAuthentication(info.getSubject());
            } catch (Exception e) {
                log.error(e.getMessage());
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
        log.info("End JwtAuthorizationFilter");
    }

    // 인증 처리
    public void setAuthentication(String loginId) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(loginId);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String loginId) {
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginId);//UserDetailsImpl
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}