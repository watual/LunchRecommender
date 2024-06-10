package com.sparta.lunchrecommender.domain.like.controller;

import com.sparta.lunchrecommender.global.dto.HttpResponseDto;
import com.sparta.lunchrecommender.domain.like.dto.LikeRequestDto;
import com.sparta.lunchrecommender.domain.like.dto.LikeResponseDto;
import com.sparta.lunchrecommender.global.security.UserDetailsImpl;
import com.sparta.lunchrecommender.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<HttpResponseDto> likeCount(@RequestBody LikeRequestDto likeRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(
                HttpResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("팔로우한 게시물들이 조회되었습니다.")
                        .data(likeService.likeCount(likeRequestDto))
                        .build()
        );
    }

}
