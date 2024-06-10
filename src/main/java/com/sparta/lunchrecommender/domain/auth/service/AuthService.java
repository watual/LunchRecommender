package com.sparta.lunchrecommender.domain.auth.service;

import com.sparta.lunchrecommender.domain.user.constant.Token;
import com.sparta.lunchrecommender.domain.user.entity.User;
import com.sparta.lunchrecommender.global.util.JwtUtil;
import com.sparta.lunchrecommender.domain.user.repository.UserRepository;
import com.sparta.lunchrecommender.global.util.UserUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserUtil userUtil;
    private final UserRepository userRepository;

    @Transactional
    public void tokenRegeneration(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String loginId = "";
        try {
            String tokenValue = jwtUtil.getJwtFromHeader(httpServletRequest, Token.AUTHORIZATION_HEADER);
            loginId = tokenCheck(tokenValue).getSubject();
        } catch (Exception ex) {
            log.info(Token.AUTHORIZATION_HEADER.getValue() + " 오류 : " + ex.getMessage() + Token.AUTHORIZATION_HEADER_REFRESH.getValue() + " 확인");

            String tokenValue = jwtUtil.getJwtFromHeader(httpServletRequest, Token.AUTHORIZATION_HEADER_REFRESH);
            loginId = tokenCheck(tokenValue).getSubject();
        }
        jwtUtil.generateTokenAndResponse(httpServletResponse, loginId);
        log.info("재발급 완료");
    }

    private Claims tokenCheck(String tokenValue) {
        if (!StringUtils.hasText(tokenValue)) {
            throw new NullPointerException("Header Empty");
        }
        if (!jwtUtil.validateToken(tokenValue)) {
            throw new IllegalArgumentException("Token Validate Error");
        }
        Claims claims = jwtUtil.getUserInfoFromToken(tokenValue);
        if (!claims.get(Token.TOKEN_TYPE.getValue(), String.class).equals(Token.TOKEN_TYPE_REFRESH.getValue())) {
            throw new IllegalArgumentException("Token Type Error");
        }
        String loginId = claims.getSubject();
        User user = userUtil.userVerifyByLoginId(loginId);
        if(!user.getRefresh_token().equals(tokenValue)) {
            throw new IllegalArgumentException("유효하지 않은 Refresh Token 입니다");
        }
        return claims;
    }

}
