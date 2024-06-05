package com.sparta.lunchrecommender.controller;

import com.sparta.lunchrecommender.dto.PostResponseDto;
import com.sparta.lunchrecommender.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<?> getPosts(){

        List<PostResponseDto> posts = postService.getPosts();

        //가져온 Post 들 비어있는지 체크
        if(posts.isEmpty()){
            return ResponseEntity.ok("먼저 작성하여 소식을 알려보세요!");
        }
        else {
            return ResponseEntity.ok(posts);
        }
    }
}
