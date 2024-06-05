package com.sparta.lunchrecommender.dto.user;

import com.sparta.lunchrecommender.constant.UserStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    private String loginId;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String intro;
}
