package com.sparta.lunchrecommender.domain.like.dto;

import com.sparta.lunchrecommender.domain.like.constant.ContentsTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeRequestDto {
    private ContentsTypeEnum contentsType;
    private Long contentId;

//    public LikeRequestDto(Like like) {
//        this.contentsType = like.getContentsType();
//        this.contentId = like.getContentId();
//    }
}
