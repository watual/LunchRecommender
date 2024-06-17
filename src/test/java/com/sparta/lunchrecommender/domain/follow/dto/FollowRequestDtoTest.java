package com.sparta.lunchrecommender.domain.follow.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FollowRequestDtoTest {
    @Test
    public void testFollowRequestDtoCreation() {
        // Given
        Long userId = 1L;
        Long followId = 2L;

        // When
        FollowRequestDto followRequestDto = new FollowRequestDto(userId, followId);

        // Then
        assertEquals(userId, followRequestDto.getUserId());
        assertEquals(followId, followRequestDto.getFollowId());
    }
}