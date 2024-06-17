package com.sparta.lunchrecommender.domain.user.dto;

import com.sparta.lunchrecommender.domain.comment.dto.CommentRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfileRequestDtoTest {

    @Test
    public void profileRequestDtoConstructTest() {
        // given
        String name = "testName";
        String nickname = "testNickname";
        String intro = "testIntro";
        String email = "testEmail";
        String password = "testPassword";

        // when
        ProfileRequestDto profileRequestDto = ProfileRequestDto.builder()
                .name(name)
                .nickname(nickname)
                .intro(intro)
                .email(email)
                .password(password)
                .build();

        // then
        assertThat(profileRequestDto.getName()).isEqualTo(name);
        assertThat(profileRequestDto.getNickname()).isEqualTo(nickname);
        assertThat(profileRequestDto.getIntro()).isEqualTo(intro);
        assertThat(profileRequestDto.getEmail()).isEqualTo(email);
        assertThat(profileRequestDto.getPassword()).isEqualTo(password);
    }

    @Test
    @DisplayName("ProfileRequestDto 무결성 검사")
    public void profileRequestDtoValidTest() {
        // given
        String name = "testName";
        String nickname = "testNickname";
        String intro = "testIntro";
        String email = "testEmail";
        String password = "testPassword1!";

        // when
        ProfileRequestDto profileRequestDto = ProfileRequestDto.builder()
                .name(name)
                .nickname(nickname)
                .intro(intro)
                .email(email)
                .password(password)
                .build();


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CommentRequestDto>> violations1 = validator.validate(profileRequestDto);

    }
}