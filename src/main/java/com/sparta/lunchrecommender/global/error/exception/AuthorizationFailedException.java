package com.sparta.lunchrecommender.global.error.exception;

public class AuthorizationFailedException extends RuntimeException{
    public AuthorizationFailedException(String message) {
        super(message);
    }
}
