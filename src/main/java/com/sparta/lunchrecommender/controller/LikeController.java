package com.sparta.lunchrecommender.controller;

import com.sparta.lunchrecommender.dto.HttpResponseDto;
import com.sparta.lunchrecommender.dto.like.LikeRequestDto;
import com.sparta.lunchrecommender.dto.like.LikeResponseDto;
import com.sparta.lunchrecommender.security.UserDetailsImpl;
import com.sparta.lunchrecommender.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<HttpResponseDto> likeCreate(@RequestBody LikeRequestDto likeRequestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl user){
        return likeService.likeCreate(likeRequestDto,user);
    }

    @DeleteMapping
    public ResponseEntity<HttpResponseDto> likeCancel(@RequestBody LikeRequestDto likeRequestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl user){
        return likeService.likeCancel(likeRequestDto,user);
    }


    @GetMapping
    public LikeResponseDto likeCount(@RequestBody LikeRequestDto likeRequestDto){
        return likeService.likeCount(likeRequestDto);
    }

}
