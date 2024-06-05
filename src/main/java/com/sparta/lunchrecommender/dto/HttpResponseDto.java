package com.sparta.lunchrecommender.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class HttpResponseDto {
//    private HttpStatus status;
    private final int statusCode;
    private String message;

    public HttpResponseDto(HttpStatus status, String message){
//        this.status = status;
        this.statusCode = status.value();
        this.message = message;
    }
}
