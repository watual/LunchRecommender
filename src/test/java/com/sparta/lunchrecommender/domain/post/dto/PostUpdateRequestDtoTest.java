package com.sparta.lunchrecommender.domain.post.dto;

import com.sparta.lunchrecommender.domain.post.entity.Post;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostUpdateRequestDtoTest {
    @Test
    void postUpdateRequestDtoTest() {
        // given
        String content = "testContent";

        // when
        PostUpdateRequestDto postUpdateRequestDto = new PostUpdateRequestDto(content);

        // then
        assertEquals(content, postUpdateRequestDto.getContent());
    }
}