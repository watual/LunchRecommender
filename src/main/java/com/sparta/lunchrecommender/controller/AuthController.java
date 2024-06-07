package com.sparta.lunchrecommender.controller;

import com.sparta.lunchrecommender.dto.HttpResponseDto;
import com.sparta.lunchrecommender.dto.user.UserRequestDto;
import com.sparta.lunchrecommender.jwt.JwtUtil;
import com.sparta.lunchrecommender.service.AuthService;
import com.sparta.lunchrecommender.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<HttpResponseDto> refresh(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        log.info("토큰 재발급 요청");
        authService.tokenRegeneration(httpServletRequest, httpServletResponse);
        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "재발급 완료"), HttpStatus.OK);
    }
}
