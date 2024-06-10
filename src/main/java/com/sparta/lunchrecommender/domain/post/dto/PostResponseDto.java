package com.sparta.lunchrecommender.domain.post.dto;

import com.sparta.lunchrecommender.domain.post.entity.Post;
import com.sparta.lunchrecommender.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private Long postid;
    private String loginid;
    private String nickname;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public PostResponseDto(Post post, User user) {
        this.postid = post.getPostId();
        this.loginid = user.getLoginId();
        this.nickname = user.getNickname();
        this.content = post.getContent();
        this.created_at = post.getCreatedAt();
        this.modified_at = post.getModifiedAt();
    }

    }
