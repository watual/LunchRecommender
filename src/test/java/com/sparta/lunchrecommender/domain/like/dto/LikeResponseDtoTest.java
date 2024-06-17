package com.sparta.lunchrecommender.domain.like.dto;

import com.sparta.lunchrecommender.domain.like.constant.ContentsTypeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LikeResponseDtoTest {
    @Test
    void likeResponseDtoTest() {
        // given
        ContentsTypeEnum contentsTypeEnum = ContentsTypeEnum.COMMENT;
        Long contentId = 7L;
        Long likeCount = 10L;

        // when
        LikeResponseDto likeResponseDto = new LikeResponseDto(contentsTypeEnum, contentId, likeCount);

        // then
        assertEquals(contentsTypeEnum, likeResponseDto.getContentsType());
        assertEquals(contentId, likeResponseDto.getContentId());
        assertEquals(likeCount, likeResponseDto.getLikeCount());
    }
}