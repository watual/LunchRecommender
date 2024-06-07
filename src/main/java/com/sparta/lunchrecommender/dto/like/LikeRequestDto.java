package com.sparta.lunchrecommender.dto.like;

import com.sparta.lunchrecommender.constant.ContentsTypeEnum;
import com.sparta.lunchrecommender.entity.Like;
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
