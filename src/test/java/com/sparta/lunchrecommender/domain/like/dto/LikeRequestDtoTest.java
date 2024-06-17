package com.sparta.lunchrecommender.domain.like.dto;

import com.sparta.lunchrecommender.domain.like.constant.ContentsTypeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LikeRequestDtoTest {
    @Test
    void ikeRequestDtoTest() {
        // Given
        ContentsTypeEnum contentsType = ContentsTypeEnum.POST;
        Long contentId = 100L;

        // When
        LikeRequestDto likeRequestDto = new LikeRequestDto(contentsType, contentId);

        // Then
        assertNotNull(likeRequestDto);
        assertEquals(contentsType, likeRequestDto.getContentsType());
        assertEquals(contentId, likeRequestDto.getContentId());
    }
}