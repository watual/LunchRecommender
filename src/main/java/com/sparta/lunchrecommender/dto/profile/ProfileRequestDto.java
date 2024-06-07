package com.sparta.lunchrecommender.dto.profile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ProfileRequestDto {

    @NotNull
    private String name;
    @NotNull
    private String nickname;
    @NotNull
    private String intro;
    @NotNull
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*?.,;:])[A-Za-z\\d!@#$%^&*?.,;:]{10,}$")
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }
}
