package com.sparta.lunchrecommender.dto;

import com.sparta.lunchrecommender.entity.Post;

import java.time.LocalDateTime;

public class PostResponseDto {

    private Long post_id;
    private String login_id;
    private String nickname;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public PostResponseDto(Post post) {
        this.post_id = post.getPostId();
        this.login_id = post.getLoginId();
        this.nickname = post.getNickname();
        this.content = post.getContent();
        this.created_at = post.getCreatedAt();
        this.modified_at = post.getModifiedAt();
    }

    }
