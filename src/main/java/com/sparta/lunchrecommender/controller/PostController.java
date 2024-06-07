package com.sparta.lunchrecommender.controller;

import com.sparta.lunchrecommender.dto.HttpResponseDto;
import com.sparta.lunchrecommender.dto.post.PostCreateRequestDto;
import com.sparta.lunchrecommender.dto.post.PostResponseDto;
import com.sparta.lunchrecommender.dto.post.PostUpdateRequestDto;
import com.sparta.lunchrecommender.security.UserDetailsImpl;
import com.sparta.lunchrecommender.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/getList")
    public ResponseEntity<?> getPosts() {

        List<PostResponseDto> posts = postService.getPosts();

        //가져온 Posts 비어있는지 체크
        if (posts.isEmpty()) {
            return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "먼저 작성하여 소식을 알려보세요!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<HttpResponseDto> createPost(@RequestBody PostCreateRequestDto requestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.createPost(requestDto, userDetails.getUser());
        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "게시물이 작성되었습니다!"), HttpStatus.OK);
    }

    @PatchMapping("/post/{post_Id}")
    public ResponseEntity<HttpResponseDto> updatePost(@PathVariable Long post_Id,
                                                      @RequestBody PostUpdateRequestDto requestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.updatePost(post_Id, requestDto, userDetails.getUser());
        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "게시물 수정되었습니다!"), HttpStatus.OK);
    }

    @DeleteMapping("/post/{post_Id}")
    public ResponseEntity<?> deletePost(@PathVariable Long post_Id,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(post_Id, userDetails.getUser());
        return new ResponseEntity<>("일정이 성공적으로 삭제되었습니다.", HttpStatus.OK);
    }
}
