package com.sparta.lunchrecommender.domain.comment.dto;

import com.sparta.lunchrecommender.domain.comment.entity.Comment;
import com.sparta.lunchrecommender.domain.post.entity.Post;
import com.sparta.lunchrecommender.domain.user.entity.User;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommentResponseDtoTest {

    @Test
    void commentRequestDtoTest() {
        // given
        Long commentId = 1L;
        String content = "댓글내용";
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime modifiedAt = LocalDateTime.now();
        Long postId = 2L;
        Long userId = 3L;
        Post post = new Post();
        post.setPostId(postId);
        User user = new User();
        user.setUserId(userId);

        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setContent(content);
        comment.setCreatedAt(createdAt);
        comment.setModifiedAt(modifiedAt);
        comment.setPost(post);
        comment.setUser(user);

        // when
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment);

        // then
        assertThat(commentResponseDto.getId()).isEqualTo(commentId);
        assertThat(commentResponseDto.getContent()).isEqualTo(content);
        assertThat(commentResponseDto.getCreatedAt()).isEqualTo(createdAt);
        assertThat(commentResponseDto.getModifiedAt()).isEqualTo(modifiedAt);
        assertThat(commentResponseDto.getPost_id()).isEqualTo(postId);
        assertThat(commentResponseDto.getUser_id()).isEqualTo(userId);
    }
}