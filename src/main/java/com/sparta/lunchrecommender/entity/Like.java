package com.sparta.lunchrecommender.entity;

import com.sparta.lunchrecommender.constant.ContentsTypeEnum;
import com.sparta.lunchrecommender.dto.like.LikeRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "liked")
@NoArgsConstructor
public class Like extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    //@OneToOne(mappedBy = "user")
    @Column
    private Long userId;
    @Column
    private ContentsTypeEnum contentsType;
    @Column
    private Long contentId;



//    public Like(Long userId, ContentsTypeEnum contentsType, Long contentId) {
//        this.userId = userId;
//        this.contentsType = contentsType;
//        this.contentId = contentId;
//    }

    public Like(LikeRequestDto request){
        this.contentsType = request.getContentsType();
        this.contentId = request.getContentId();
    }

//    public void setUserId(Long userId){
//        this.userId = userId;
//    }
}