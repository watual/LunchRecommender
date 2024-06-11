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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserUtil userUtil;
    private final UserRepository userRepository;

    @Transactional
    public Map<String, Object> tokenRegeneration(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String loginId = "";
        String tokenValue = "";
        try {
            tokenValue = jwtUtil.getJwtFromHeader(httpServletRequest, Token.AUTHORIZATION_HEADER);
            loginId = tokenCheck(tokenValue).getSubject();
        } catch (Exception ex) {
            log.info(Token.AUTHORIZATION_HEADER.getValue() + " 오류 : " + ex.getMessage() + Token.AUTHORIZATION_HEADER_REFRESH.getValue() + " 확인");

            tokenValue = jwtUtil.getJwtFromHeader(httpServletRequest, Token.AUTHORIZATION_HEADER_REFRESH);
            loginId = tokenCheck(tokenValue).getSubject();
        }

        String refreshToken = jwtUtil.generateTokenAndResponse(httpServletResponse, loginId);
        log.info("재발급 완료");

        Map<String, Object> tokenList = new HashMap<>();
        tokenList.put("이전 토큰", tokenValue);
        tokenList.put("재발급 토큰", refreshToken);
        return tokenList;
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
