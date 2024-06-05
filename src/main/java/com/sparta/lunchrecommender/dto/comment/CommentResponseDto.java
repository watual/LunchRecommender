package com.sparta.lunchrecommender.dto.comment;

import com.sparta.lunchrecommender.entity.Comment;
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
    //private final Long user_id;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        // this.like_count = comment.getLike_count();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.post_id=comment.getPost().getPostId();
    }
}