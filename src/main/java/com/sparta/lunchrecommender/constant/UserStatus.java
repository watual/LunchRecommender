package com.sparta.lunchrecommender.constant;

import lombok.Getter;

@Getter
public enum UserStatus {
    UNAUTHORIZED("인증 전"),
    ACTIVE("정상"),
    DELETED("탈퇴");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }
}
