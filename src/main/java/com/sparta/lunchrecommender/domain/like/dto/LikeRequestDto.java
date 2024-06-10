package com.sparta.lunchrecommender.domain.like.dto;

import com.sparta.lunchrecommender.domain.like.constant.ContentsTypeEnum;
import lombok.Getter;

@Getter
public class LikeRequestDto {
    private ContentsTypeEnum contentsType;
    private Long contentId;

//    public LikeRequestDto(Like like) {
//        this.contentsType = like.getContentsType();
//        this.contentId = like.getContentId();
//    }
}
