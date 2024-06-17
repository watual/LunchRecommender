package com.sparta.lunchrecommender.domain.post.entity;

import com.sparta.lunchrecommender.domain.post.dto.PostCreateRequestDto;
import com.sparta.lunchrecommender.domain.post.dto.PostUpdateRequestDto;
import com.sparta.lunchrecommender.domain.user.constant.UserStatus;
import com.sparta.lunchrecommender.domain.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    User user;

    @BeforeEach
    void setUp() {
        String loginId = "testLoginId";
        String password = "testPassword";
        String name = "tester";
        String nickname = "testNickname";
        String email = "test@gmail.com";
        String intro = "testIntro";
        UserStatus status = UserStatus.UNAUTHORIZED;
        user = new User(loginId, password, name, nickname, email, intro, status);
    }

    @Test
    public void postConstructTest() {
        // Given
        String content = "testContent";
        PostCreateRequestDto postCreateRequestDto = new PostCreateRequestDto(content);
        Long likeCount = 30L;

        // when
        Post post = new Post(postCreateRequestDto);
        post.setLikeCount(likeCount);
        post.setUser(user);

        // then
        assertThat(post.getPostId()).isNull();
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getLikeCount()).isEqualTo(likeCount);
        assertThat(post.getUser()).isEqualTo(user);
    }

    @Test
    public void postUpdateTest() {
        // Given
        String initialContent = "Initial content";
        PostCreateRequestDto createRequestDto = new PostCreateRequestDto(initialContent);
        Post post = new Post(createRequestDto);

        // Update 정보
        String updatedContent = "Updated content";
        PostUpdateRequestDto updateRequestDto = new PostUpdateRequestDto(updatedContent);

        // When
        post.update(updateRequestDto);

        // Then
        assertEquals(updatedContent, post.getContent());
    }
}