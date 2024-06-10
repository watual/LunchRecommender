package com.sparta.lunchrecommender.error.exception;

public class AuthorizationFailedException extends RuntimeException{
    public AuthorizationFailedException(String message) {
        super(message);
    }
}
