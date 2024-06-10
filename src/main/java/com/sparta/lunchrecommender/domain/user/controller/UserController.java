package com.sparta.lunchrecommender.domain.user.controller;

import com.sparta.lunchrecommender.global.dto.HttpResponseDto;
import com.sparta.lunchrecommender.domain.user.dto.PasswordRequestDto;
import com.sparta.lunchrecommender.domain.user.dto.UserRequestDto;
import com.sparta.lunchrecommender.global.security.UserDetailsImpl;
import com.sparta.lunchrecommender.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<HttpResponseDto> signup(@Valid @RequestBody UserRequestDto requestDto) {
        log.info("회원가입 요청");
        userService.signup(requestDto);
        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "회원가입 성공, 메일을 인증해주세요"), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpResponseDto> logout(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        log.info("로그아웃 요청");
        userService.logout(userDetailsImpl.getUser().getUserId());
        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "로그아웃 완료"), HttpStatus.OK);
    }

    @PatchMapping("/deleteAccount")
    public ResponseEntity<HttpResponseDto> deleteAccount(
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @RequestBody PasswordRequestDto passwordRequestDto
    ) {
        userService.deleteAccount(userDetailsImpl.getUser().getLoginId(), passwordRequestDto);
        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "회원탈퇴가 완료되었습니다."), HttpStatus.OK);
    }
}
