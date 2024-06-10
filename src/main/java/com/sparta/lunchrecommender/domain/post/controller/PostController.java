package com.sparta.lunchrecommender.domain.post.controller;

import com.sparta.lunchrecommender.global.dto.HttpResponseDto;
import com.sparta.lunchrecommender.domain.post.dto.PostCreateRequestDto;
import com.sparta.lunchrecommender.domain.post.dto.PostResponseDto;
import com.sparta.lunchrecommender.domain.post.dto.PostUpdateRequestDto;
import com.sparta.lunchrecommender.global.security.UserDetailsImpl;
import com.sparta.lunchrecommender.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/getList")
    public ResponseEntity<HttpResponseDto> getPosts(
            @RequestParam(value = "page", defaultValue = "0") int page, // 사용자가 입력하지 않았다면 첫번째 페이지(0)를 반환함
            @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy, // 사용자가 입력하지 않았다면 생성일자순으로 반환함
            @RequestParam(value = "startDate", required = false) String startDateStr,
            @RequestParam(value = "endDate", required = false) String endDateStr
    ) {
        List<String> sortable = Arrays.asList("createdAt", "likeCount"); // 생성일자와 좋아요순으로만 조회 가능
        if (!sortable.contains(sortBy)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    HttpResponseDto.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message("유효하지 않은 정렬 기준입니다.")
                            .build()
            );
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        try {
            if (startDateStr != null) {
                startDate = LocalDate.parse(startDateStr, formatter).atStartOfDay();
            }
            if (endDateStr != null) {
                endDate = LocalDate.parse(endDateStr, formatter).atTime(23, 59, 59);
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    HttpResponseDto.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message("잘못된 날짜 형식입니다.")
                            .build()
            );
        }

        Page<PostResponseDto> posts = postService.getPosts(page, sortBy, startDate, endDate);
        if (posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    HttpResponseDto.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("먼저 작성하여 소식을 알려보세요!")
                            .build()
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    HttpResponseDto.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("게시물 조회가 완료 되었습니다.")
                            .data(posts)
                            .build()
            );
        }
    }

    @PostMapping("/post")
    public ResponseEntity<HttpResponseDto> createPost(@RequestBody PostCreateRequestDto requestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.createPost(requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(
                HttpResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("게시물 작성이 완료되었습니다.")
                        .build()
        );
    }

    @PatchMapping("/post/{post_Id}")
    public ResponseEntity<HttpResponseDto> updatePost(@PathVariable Long post_Id,
                                                      @RequestBody PostUpdateRequestDto requestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.updatePost(post_Id, requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(
                HttpResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("게시물 수정이 완료되었습니다.")
                        .build()
        );
    }

    @DeleteMapping("/post/{post_Id}")
    public ResponseEntity<HttpResponseDto> deletePost(@PathVariable Long post_Id,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(post_Id, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(
                HttpResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("게시물 삭제가 완료되었습니다.")
                        .build()
        );
    }
}
