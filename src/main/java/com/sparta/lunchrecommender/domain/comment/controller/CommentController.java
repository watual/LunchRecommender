package com.sparta.lunchrecommender.domain.comment.controller;

import com.sparta.lunchrecommender.domain.comment.dto.CommentRequestDto;
import com.sparta.lunchrecommender.domain.comment.dto.CommentResponseDto;
import com.sparta.lunchrecommender.global.dto.HttpResponseDto;
import com.sparta.lunchrecommender.global.security.UserDetailsImpl;
import com.sparta.lunchrecommender.domain.comment.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class CommentController {
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{post_id}/comment")
    public ResponseEntity<HttpResponseDto> addComment(@PathVariable Long post_id,
                                                      @RequestBody CommentRequestDto commentRequestDto,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(
                HttpResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("댓글이 작성이 완료되었습니다.")
                        .data(commentService.addComment(post_id, commentRequestDto, userDetails.getUser()))
                        .build()
        );
    }

    @GetMapping("/{post_id}/comment/{comment_id}")
    public ResponseEntity<HttpResponseDto> findCommentById(@PathVariable("post_id") Long post_id,
                                              @PathVariable("comment_id") Long comment_id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                HttpResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("댓글 조회가 완료되었습니다.")
                        .data(commentService.findCommentById(post_id, comment_id))
                        .build()
        );
    }

    @GetMapping("/{post_id}/comment/getList")
    public ResponseEntity<HttpResponseDto> findCommentAll(@PathVariable Long post_id) {
        List<CommentResponseDto> comments = commentService.findCommentAll(post_id);
        if (comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    HttpResponseDto.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("가장 먼저 댓글을 남겨보세요!")
                            .build()
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    HttpResponseDto.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("게시물의 모든 댓글 조회 완료되었습니다.")
                            .data(comments)
                            .build()
            );
        }
    }

    @PatchMapping("/{post_id}/comment/{comment_id}")
    public ResponseEntity<HttpResponseDto> updateComment(@PathVariable("post_id") Long post_id,
                                            @PathVariable("comment_id") Long comment_id,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(
                HttpResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("댓글 수정이 완료되었습니다.")
                        .data(commentService.updateComment(post_id, comment_id, commentRequestDto, userDetails.getUser()))
                        .build()
        );
    }

    @DeleteMapping("/{post_id}/comment/{comment_id}")
    public ResponseEntity<HttpResponseDto> deleteComment(@PathVariable("post_id") Long post_id,
                                                @PathVariable("comment_id") Long comment_id,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(post_id, comment_id, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(
                HttpResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("댓글 삭제가 완료되었습니다.")
                        .build()
        );
    }
}
