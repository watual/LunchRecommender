package com.sparta.lunchrecommender.domain.like.repository;

import com.sparta.lunchrecommender.domain.like.constant.ContentsTypeEnum;
import com.sparta.lunchrecommender.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByUserIdAndContentsTypeAndContentId(Long userId, ContentsTypeEnum contentsTypeEnum, Long contentId);
}
