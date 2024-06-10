package com.sparta.lunchrecommender.dto.follow;

import lombok.Getter;

@Getter
public class FollowRequestDto {
    private Long userId;
    private Long followId;

    public FollowRequestDto(Long userId, Long followId) {
        this.userId = userId;
        this.followId = followId;
    }
}
