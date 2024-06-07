package com.sparta.lunchrecommender.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.lunchrecommender.constant.Token;
import com.sparta.lunchrecommender.dto.HttpResponseDto;
import com.sparta.lunchrecommender.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;

    public void tokenRegeneration(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String loginId = "";
        try {
            String tokenValue = jwtUtil.getJwtFromHeader(httpServletRequest, Token.AUTHORIZATION_HEADER);
            loginId = tokenCheck(tokenValue).getSubject();
        } catch (Exception ex) {
            log.info(Token.AUTHORIZATION_HEADER.getValue() + " 오류 : " + ex.getMessage() + Token.AUTHORIZATION_HEADER_REFRESH.getValue() + "확인");

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
        return claims;
    }
}
