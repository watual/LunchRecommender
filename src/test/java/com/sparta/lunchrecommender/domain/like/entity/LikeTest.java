package com.sparta.lunchrecommender.domain.like.entity;

import com.sparta.lunchrecommender.domain.like.constant.ContentsTypeEnum;
import com.sparta.lunchrecommender.domain.like.dto.LikeRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LikeTest {
    @Test
    @DisplayName("like Entity 생성자 테스트")
    void likeConstructTest() {
        // given
        ContentsTypeEnum contentsTypeEnum = ContentsTypeEnum.POST;
        Long contentId = 20L;
        LikeRequestDto likeRequestDto = new LikeRequestDto(contentsTypeEnum, contentId);

        // when
        Like like = new Like(likeRequestDto);

        // then
        assertNull(like.getLikeId());
        assertNull(like.getUserId());
        assertEquals(contentsTypeEnum, like.getContentsType());
        assertEquals(contentId, like.getContentId());
    }
}