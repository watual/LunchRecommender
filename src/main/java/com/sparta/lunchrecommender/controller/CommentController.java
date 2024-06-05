package com.sparta.lunchrecommender.controller;

import com.sparta.lunchrecommender.dto.comment.CommentRequestDto;
import com.sparta.lunchrecommender.dto.comment.CommentResponseDto;
import com.sparta.lunchrecommender.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{post_id}/comment")
    public CommentResponseDto addComment(@PathVariable Long post_id,
                                         @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.addComment(post_id, commentRequestDto);
    }
}
