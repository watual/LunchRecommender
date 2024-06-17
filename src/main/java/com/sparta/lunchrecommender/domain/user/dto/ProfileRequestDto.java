package com.sparta.lunchrecommender.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class ProfileRequestDto {

    @NotNull(message = "이름은 필수입니다.")
    private String name;
    @NotNull(message = "닉네임은 필수입니다.")
    private String nickname;
    @NotNull(message = "자기소개는 필수입니다.")
    private String intro;
    @Email
    private String email;
    @Setter
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*?.,;:])[A-Za-z\\d!@#$%^&*?.,;:]{10,}$")
    private String password;
}