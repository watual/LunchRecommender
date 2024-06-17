package com.sparta.lunchrecommender.domain.post.dto;

import com.sparta.lunchrecommender.domain.post.entity.Post;
import com.sparta.lunchrecommender.domain.user.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostResponseDtoTest {
    @Test
    void postResponseDetTest() {
        // given
        Long postId = 10L;
        String loginId = "testLoginId";
        String nickname = "testNickname";
        String content = "testContent";
        LocalDateTime created_at = LocalDateTime.now();
        LocalDateTime modified_at = LocalDateTime.now().plusHours(5);
        Post post = new Post();
        post.setPostId(postId);
        post.setContent(content);
        post.setCreatedAt(created_at);
        post.setModifiedAt(modified_at);
        User user = new User();
        user.setLoginId(loginId);
        user.setNickname(nickname);

        // when
        PostResponseDto postResponseDto = new PostResponseDto(post, user);

        // then
        assertEquals(postId, postResponseDto.getPostid());
        assertEquals(loginId, postResponseDto.getLoginid());
        assertEquals(nickname, postResponseDto.getNickname());
        assertEquals(content, postResponseDto.getContent());
        assertEquals(created_at, postResponseDto.getCreated_at());
        assertEquals(modified_at, postResponseDto.getModified_at());
    }
}