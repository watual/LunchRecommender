package com.sparta.lunchrecommender.repository;

import com.sparta.lunchrecommender.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
