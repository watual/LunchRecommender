package com.sparta.lunchrecommender.global.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class HttpResponseDto {
    private HttpStatus status;
    private final int statusCode;
    private String message;
    private Object data;


    public HttpResponseDto(HttpStatus status, int statusCode, String message, Object data){
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }
    public HttpResponseDto(HttpStatus status, String message, Object data){
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.data = data;
    }
    public HttpResponseDto(HttpStatus status, String message){
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
    }
}
