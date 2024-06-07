package com.sparta.lunchrecommender.repository;

import com.sparta.lunchrecommender.constant.ContentsTypeEnum;
import com.sparta.lunchrecommender.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByUserIdAndContentsTypeAndContentId(Long userId, ContentsTypeEnum contentsTypeEnum, Long contentId);
}
