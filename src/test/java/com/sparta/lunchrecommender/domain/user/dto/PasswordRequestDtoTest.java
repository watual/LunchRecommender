package com.sparta.lunchrecommender.domain.user.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PasswordRequestDtoTest {
    @Test
    void passwordRequestDtoTest() {

        // given
        String password = "testPassword";
        PasswordRequestDto passwordRequestDto = new PasswordRequestDto();

        // when
        passwordRequestDto.setPassword(password);

        // then
        assertThat(passwordRequestDto.getPassword()).isEqualTo(password);
    }

}