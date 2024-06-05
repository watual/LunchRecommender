package com.sparta.lunchrecommender.dto.profile;

import com.sparta.lunchrecommender.entity.TmpUserSM;
import lombok.Getter;

@Getter
public class ProfileResponseDto {
    private String loginId;
    private String username;
    private String nickname;
    private String intro;
    private String email;

    public ProfileResponseDto(TmpUserSM user) {
        this.loginId = user.getLoginid();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.intro = user.getIntro();
        this.email = user.getEmail();
    }
}