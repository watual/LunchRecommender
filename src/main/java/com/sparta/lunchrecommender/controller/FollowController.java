package com.sparta.lunchrecommender.controller;

import com.sparta.lunchrecommender.dto.HttpResponseDto;
import com.sparta.lunchrecommender.security.UserDetailsImpl;
import com.sparta.lunchrecommender.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow/{userId}")
    public ResponseEntity<HttpResponseDto> likeCreate(@PathVariable Long userId,
                                                      @AuthenticationPrincipal UserDetailsImpl user){
        return followService.follow(userId,user);
    }

    @DeleteMapping("/unfollow/{userId}")
    public ResponseEntity<HttpResponseDto> likeCancel(@PathVariable Long userId,
                                                      @AuthenticationPrincipal UserDetailsImpl user){
        return followService.unfollow(userId,user);
    }


}
