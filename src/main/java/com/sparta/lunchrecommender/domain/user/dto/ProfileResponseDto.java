package com.sparta.lunchrecommender.domain.user.dto;

import com.sparta.lunchrecommender.domain.user.entity.User;
import lombok.Getter;

@Getter
public class ProfileResponseDto {
    private String loginId;
    private String name;
    private String nickname;
    private String intro;
    private String email;

    public ProfileResponseDto(User user) {
        this.loginId = user.getLoginId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.intro = user.getIntro();
        this.email = user.getEmail();
    }
}