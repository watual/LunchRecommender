package com.sparta.lunchrecommender.jwt;

import com.sparta.lunchrecommender.constant.Token;
import com.sparta.lunchrecommender.entity.User;
import com.sparta.lunchrecommender.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j(topic = "JwtUtil")
@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final UserRepository userRepository;
    // Token 식별자
    public static final String BEARER_PREFIX = "Bearer ";

    @Value("${jwt.secret.key}") // Base64 Encode 한 SecretKey
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // 토큰 생성
    public String createToken(String loginId, Token tokenType) {
        Date date = new Date();
        // 토큰 말료 시간
        Long tokenTime = 0L;
        if (tokenType == Token.TOKEN_TYPE_ACCESS) {
            tokenTime = 1000L * 60 * 30;            // 30분
        } else if (tokenType == Token.TOKEN_TYPE_REFRESH) {
            tokenTime = 1000L * 60 * 60 * 24 * 14;  // 2주
        } else {
            throw new IllegalArgumentException("Illegal Token Type Error");
        }

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(loginId) // 사용자 식별자값(로그인 ID)
                        .setExpiration(new Date(date.getTime() + tokenTime)) // 만료 시간
                        .claim(Token.TOKEN_TYPE.getValue(), tokenType.getValue())
                        .setIssuedAt(date) // 발급일
                        .signWith(key, signatureAlgorithm) // 암호화 알고리즘
                        .compact();
    }

    // header 에서 JWT 가져오기
    public String getJwtFromHeader(HttpServletRequest request, Token tokenType) {
        String bearerToken = request.getHeader(tokenType.getValue());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    @Transactional
    // 토큰 발급
    public void generateTokenAndResponse(HttpServletResponse httpServletResponse, String loginId) throws IOException {
        String refreshToken = createToken(loginId, Token.TOKEN_TYPE_REFRESH);
        httpServletResponse.addHeader(
                Token.AUTHORIZATION_HEADER.getValue(),
                createToken(loginId, Token.TOKEN_TYPE_ACCESS));
        httpServletResponse.addHeader(
                Token.AUTHORIZATION_HEADER_REFRESH.getValue(),
                refreshToken);
        User user = userRepository.findByLoginId(loginId).orElseThrow();
        user.setRefresh_token(refreshToken.substring(7));
    }
}
