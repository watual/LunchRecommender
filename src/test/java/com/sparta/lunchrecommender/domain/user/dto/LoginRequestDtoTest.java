package com.sparta.lunchrecommender.domain.user.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDtoTest {
    @Test
    void loginRequestDtoTest() {
        // given
        String loginId = "testLoginId";
        String password = "testPassword";
        LoginRequestDto loginRequestDto = new LoginRequestDto();

        // when
        loginRequestDto.setLoginId(loginId);
        loginRequestDto.setPassword(password);

        // then
        assertThat(loginRequestDto.getLoginId()).isEqualTo(loginId);
        assertThat(loginRequestDto.getPassword()).isEqualTo(password);
    }
}