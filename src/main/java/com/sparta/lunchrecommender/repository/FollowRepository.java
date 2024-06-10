package com.sparta.lunchrecommender.repository;

import com.sparta.lunchrecommender.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository  extends JpaRepository<Follow, Long> {
    Optional<Follow> findByUserIdAndFolloweeId(Long userId, Long followeeId);
}
