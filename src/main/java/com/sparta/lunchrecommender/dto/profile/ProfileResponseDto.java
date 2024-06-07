package com.sparta.lunchrecommender.dto.profile;

import com.sparta.lunchrecommender.entity.User;
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