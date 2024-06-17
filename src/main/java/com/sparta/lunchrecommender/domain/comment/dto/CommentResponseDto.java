package com.sparta.lunchrecommender.domain.comment.dto;

import com.sparta.lunchrecommender.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String content;
    //private final Long like_count;

    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private final Long post_id;
    private final Long user_id;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getCommentId();
        this.content = comment.getContent();
        // this.like_count = comment.getLike_count();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.post_id = comment.getPost().getPostId();
        this.user_id = comment.getUser().getUserId();
    }
}
