package com.sparta.lunchrecommender.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    @Pattern(regexp = "^[A-Za-z\\d]{10,20}$")
    private String loginId;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*?.,;:])[A-Za-z\\d!@#$%^&*?.,;:]{10,}$")
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String nickname;
    @Email
    private String email;
    private String intro;
}
