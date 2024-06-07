package com.sparta.lunchrecommender.dto.like;

import com.sparta.lunchrecommender.constant.ContentsTypeEnum;
import com.sparta.lunchrecommender.entity.Like;
import lombok.Getter;

@Getter
public class LikeRequestDto {
    private Long userId;
    private ContentsTypeEnum contentsType;
    private Long contentId;

    public LikeRequestDto(Like like) {
        this.userId = like.getUserId();
        this.contentsType = like.getContentsType();
        this.contentId = like.getContentId();
    }
}
