package com.sparta.lunchrecommender.domain.auth.controller;

import com.sparta.lunchrecommender.domain.auth.repository.VerificationTokenRepository;
import com.sparta.lunchrecommender.domain.auth.service.AuthService;
import com.sparta.lunchrecommender.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    private AuthController authController;

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    private AuthService authService;
    @Mock
    private UserService userService;
    @Mock
    private VerificationTokenRepository verificationTokenRepository;


    void refreshTest() {
        // given

    }
}