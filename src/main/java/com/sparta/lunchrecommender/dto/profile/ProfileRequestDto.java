package com.sparta.lunchrecommender.dto.profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ProfileRequestDto {

    @NotNull(message = "이름은 필수입니다.")
    private String name;
    @NotNull(message = "닉네임은 필수입니다.")
    private String nickname;
    @NotNull(message = "자기소개는 필수입니다.")
    private String intro;
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*?.,;:])[A-Za-z\\d!@#$%^&*?.,;:]{10,}$")
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }
}
