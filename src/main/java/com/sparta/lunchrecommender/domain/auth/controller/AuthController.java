package com.sparta.lunchrecommender.domain.auth.controller;

import com.sparta.lunchrecommender.global.dto.HttpResponseDto;
import com.sparta.lunchrecommender.domain.auth.repository.VerificationTokenRepository;
import com.sparta.lunchrecommender.domain.auth.service.AuthService;
import com.sparta.lunchrecommender.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final VerificationTokenRepository verificationTokenRepository;

    @PostMapping("/refresh")
    public ResponseEntity<HttpResponseDto> refresh(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        log.info("토큰 재발급 요청");
        authService.tokenRegeneration(httpServletRequest, httpServletResponse);
        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "재발급 완료"), HttpStatus.OK);
    }

    @GetMapping("/confirm")
    public ResponseEntity<HttpResponseDto> confirmToken(@RequestParam("token") String token) {
        log.info("메일 인증");
        return userService.confirmEmailToken(token);
    }
}
