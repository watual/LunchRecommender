package com.sparta.lunchrecommender.dto.profile;

import lombok.Getter;

@Getter
public class ProfileRequestDto {
    private String username;
    private String nickname;
    private String intro;
    private String email;
    private String password;
}
