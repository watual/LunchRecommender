package com.sparta.lunchrecommender.domain.like.entity;

import com.sparta.lunchrecommender.domain.like.constant.ContentsTypeEnum;
import com.sparta.lunchrecommender.domain.like.dto.LikeRequestDto;
import com.sparta.lunchrecommender.global.util.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "liked")
@NoArgsConstructor
public class Like extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    @Column
    private Long userId;
    @Column
    private ContentsTypeEnum contentsType;
    @Column
    private Long contentId;
    

    public Like(LikeRequestDto request){
        this.contentsType = request.getContentsType();
        this.contentId = request.getContentId();
    }
}