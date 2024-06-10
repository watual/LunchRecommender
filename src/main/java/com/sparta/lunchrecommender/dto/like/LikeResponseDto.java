package com.sparta.lunchrecommender.dto.like;

import com.sparta.lunchrecommender.constant.ContentsTypeEnum;

public class LikeResponseDto {
    public ContentsTypeEnum contentsType;
    public Long contentId;
    public Long likeCount;


    public LikeResponseDto(ContentsTypeEnum contentsType, Long contentId, Long likeCount) {
        this.contentsType = contentsType;
        this.contentId = contentId;
        this.likeCount = likeCount;
    }
}
