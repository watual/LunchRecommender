package com.sparta.lunchrecommender.domain.follow.controller;

import com.sparta.lunchrecommender.global.dto.HttpResponseDto;
import com.sparta.lunchrecommender.domain.post.entity.Post;
import com.sparta.lunchrecommender.global.security.UserDetailsImpl;
import com.sparta.lunchrecommender.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow/{userId}")
    public ResponseEntity<HttpResponseDto> follow(@PathVariable Long userId,
                                                      @AuthenticationPrincipal UserDetailsImpl user){
        return followService.follow(userId,user);
    }

    @DeleteMapping("/unfollow/{userId}")
    public ResponseEntity<HttpResponseDto> unfollow(@PathVariable Long userId,
                                                      @AuthenticationPrincipal UserDetailsImpl user){
        return followService.unfollow(userId,user);
    }

    @GetMapping("/follow/post/")
    public List<Post> getFollowPost(@AuthenticationPrincipal UserDetailsImpl user){
        return followService.getFollowPost(user);
    }


}
