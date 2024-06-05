package com.sparta.lunchrecommender.controller;

import com.sparta.lunchrecommender.dto.HttpResponseDto;
import com.sparta.lunchrecommender.dto.user.UserRequestDto;
import com.sparta.lunchrecommender.jwt.JwtUtil;
import com.sparta.lunchrecommender.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<HttpResponseDto> signup(@RequestBody UserRequestDto requestDto) {
        userService.signup(requestDto);
        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "회원가입 성공"), HttpStatus.OK);
    }

}
