package com.sparta.lunchrecommender.domain.post.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostCreateRequestDtoTest {
    @Test
    void postCreateRequestDtoTest() {
        // given
        String content = "testContent1";

        // when
        PostCreateRequestDto postCreateRequestDto = new PostCreateRequestDto(content);

        // then
        assertEquals(content, postCreateRequestDto.getContent());
    }

}