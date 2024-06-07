package com.sparta.lunchrecommender.controller;

import com.sparta.lunchrecommender.dto.comment.CommentRequestDto;
import com.sparta.lunchrecommender.dto.comment.CommentResponseDto;
import com.sparta.lunchrecommender.security.UserDetailsImpl;
import com.sparta.lunchrecommender.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
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
    public ResponseEntity<?> addComment(@PathVariable Long post_id,
                                        @RequestBody CommentRequestDto commentRequestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto commentResponseDto = commentService.addComment(post_id, commentRequestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDto);
    }

    @GetMapping("/{post_id}/comment/{comment_id}")
    public ResponseEntity<?> findCommentById(@PathVariable("post_id") Long post_id,
                                             @PathVariable("comment_id") Long comment_id) {
        CommentResponseDto commentResponseDto = commentService.findCommentById(post_id, comment_id);
        return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
    }

    @GetMapping("/{post_id}/comment")
    public ResponseEntity<?> findCommentAll(@PathVariable Long post_id) {
        List<CommentResponseDto> comments = commentService.findCommentAll(post_id);
        if (comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("가장 먼저 댓글을 남겨보세요!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(comments);
        }
    }
}
