package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.dto.HttpResponseDto;
import com.sparta.lunchrecommender.dto.follow.FollowRequestDto;
import com.sparta.lunchrecommender.entity.Follow;
import com.sparta.lunchrecommender.repository.FollowRepository;
import com.sparta.lunchrecommender.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional
    public ResponseEntity<HttpResponseDto> follow(Long userId, UserDetailsImpl user) {

        if(followRepository.findByUserIdAndFolloweeId(user.getUser().getUserId(), userId).isPresent()){
            throw new IllegalArgumentException("이미 팔로우 중인 계정입니다.");
        }

        FollowRequestDto requestDto = new FollowRequestDto(user.getUser().getUserId(), userId);
        followRepository.save(new Follow(requestDto));

        return null;
    }

    @Transactional
    public ResponseEntity<HttpResponseDto> unfollow(Long userId, UserDetailsImpl user) {

        Follow follow = followRepository.findByUserIdAndFolloweeId(user.getUser().getUserId(), userId).orElseThrow(
                ()-> new NullPointerException("팔로우 중인 계정이 아닙니다.")
        );

        followRepository.delete(follow);

        return null;
    }
}
