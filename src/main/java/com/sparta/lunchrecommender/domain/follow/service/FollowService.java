package com.sparta.lunchrecommender.domain.follow.service;

import com.sparta.lunchrecommender.global.dto.HttpResponseDto;
import com.sparta.lunchrecommender.domain.follow.dto.FollowRequestDto;
import com.sparta.lunchrecommender.domain.follow.entity.Follow;
import com.sparta.lunchrecommender.domain.post.entity.Post;
import com.sparta.lunchrecommender.domain.follow.repository.FollowRepository;
import com.sparta.lunchrecommender.domain.post.repository.PostRepository;
import com.sparta.lunchrecommender.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final PostRepository postRepository;

    @Transactional
    public ResponseEntity<HttpResponseDto> follow(Long userId, UserDetailsImpl user) {

        if (followRepository.findByUserIdAndFolloweeId(user.getUser().getUserId(), userId).isPresent()) {
            throw new IllegalArgumentException("이미 팔로우 중인 계정입니다.");
        }

        FollowRequestDto requestDto = new FollowRequestDto(user.getUser().getUserId(), userId);
        followRepository.save(new Follow(requestDto));

        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "팔로우성공"), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<HttpResponseDto> unfollow(Long userId, UserDetailsImpl user) {

        Follow follow = followRepository.findByUserIdAndFolloweeId(user.getUser().getUserId(), userId).orElseThrow(
                () -> new NullPointerException("팔로우 중인 계정이 아닙니다.")
        );

        followRepository.delete(follow);

        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "언팔로우성공"), HttpStatus.OK);
    }

    public List<Post> getFollowPost(UserDetailsImpl user) {

        List<Follow> followList = followRepository.findByUserId(user.getUser().getUserId());
        if(followList.isEmpty()){
            throw new NullPointerException("팔로우 한 계정이 없습니다.");
        }


        List<Long> followeeIds =  followList.stream()
                .map(Follow::getFolloweeId)
                .collect(Collectors.toList());

        List<Post> posts = postRepository.findByUserUserIdIn(followeeIds);

        return posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed()) // 최신순으로 정렬
                .collect(Collectors.toList());
    }

}
