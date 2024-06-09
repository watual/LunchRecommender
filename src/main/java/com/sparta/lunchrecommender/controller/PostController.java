package com.sparta.lunchrecommender.controller;

import com.sparta.lunchrecommender.dto.HttpResponseDto;
import com.sparta.lunchrecommender.dto.post.PostCreateRequestDto;
import com.sparta.lunchrecommender.dto.post.PostResponseDto;
import com.sparta.lunchrecommender.dto.post.PostUpdateRequestDto;
import com.sparta.lunchrecommender.security.UserDetailsImpl;
import com.sparta.lunchrecommender.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<?> getPosts(
            @RequestParam(value = "page", defaultValue = "0") int page, // 사용자가 입력하지 않았다면 첫번째 페이지(0)를 반환함
            @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy // 사용자가 입력하지 않았다면 생성일자순으로 반환함
    ) {

        int size = 10;
        boolean isAsc = false;
        Page<PostResponseDto> posts = postService.getPosts(page, size, sortBy, isAsc);
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
