package com.sparta.lunchrecommender.constant;

import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE("정상"),
    DELETED("탈퇴");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }
}
