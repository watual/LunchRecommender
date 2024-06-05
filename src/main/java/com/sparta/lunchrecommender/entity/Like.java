package com.sparta.lunchrecommender.entity;

import com.sparta.lunchrecommender.constant.ContentsTypeEnum;
import com.sparta.lunchrecommender.dto.like.LikeRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Like extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    //@OneToOne(mappedBy = "user")
    private Long userId;
    private ContentsTypeEnum contentsType;
    private Long contentId;


    public Like(LikeRequestDto request){
        this.userId = request.getUserId();
        this.contentsType = request.getContentsType();
        this.contentId = request.getContentId();
    }
}

/*
- 사용자가 게시물이나 댓글에 좋아요를 남기거나 취소할 수 있습니다.
- 본인이 작성한 게시물과 댓글에 좋아요를 남길 수 없습니다.
- 같은 게시물에는 사용자당 한 번만 좋아요가 가능합니다.
 */