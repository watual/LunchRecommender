package com.sparta.lunchrecommender.dto.comment;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class CommentRequestDto {
    @NotBlank
    private String content;

    @JsonCreator // 필드가 1개라서 deserialize 에러 발생 -> 해결
    public CommentRequestDto(String content) {
        this.content = content;
    }
}
