package com.sparta.lunchrecommender.domain.user.constant;

import lombok.Getter;

@Getter
public enum     Token {
    TOKEN_TYPE("Token-Type"),
    AUTHORIZATION_HEADER("Authorization"),
    AUTHORIZATION_HEADER_REFRESH("Authorization-Refresh"),
    TOKEN_TYPE_ACCESS("ACCESS"),
    TOKEN_TYPE_REFRESH("REFRESH");

    private final String value;

    Token(String value) {
        this.value = value;
    }
}
