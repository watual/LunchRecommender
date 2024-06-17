package com.sparta.lunchrecommender.domain.follow.entity;

import com.sparta.lunchrecommender.domain.follow.dto.FollowRequestDto;
import com.sparta.lunchrecommender.domain.follow.repository.FollowRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class FollowTest {
    @Test
    public void testFollowEntityCreationWithDto() {
        // Given
        FollowRequestDto requestDto = new FollowRequestDto(1L, 2L);

        // When
        Follow follow = new Follow(requestDto);

        // Then
        assertNotNull(follow);
        assertNull(follow.getFollow_id()); // ID는 아직 설정되지 않았으므로 null이어야 합니다.
        assertEquals(1L, follow.getUserId());
        assertEquals(2L, follow.getFolloweeId());
    }

    @Test
    public void testFollowEntitySetters() {
        // Given
        Follow follow = new Follow();

        // When
        follow.setUserId(1L);
        follow.setFolloweeId(2L);

        // Then
        assertEquals(1L, follow.getUserId());
        assertEquals(2L, follow.getFolloweeId());
    }

    @Test
    public void testFollowEntityGetters() {
        // Given
        Follow follow = new Follow();
        follow.setUserId(1L);
        follow.setFolloweeId(2L);

        // When & Then
        assertEquals(1L, follow.getUserId());
        assertEquals(2L, follow.getFolloweeId());
    }
}