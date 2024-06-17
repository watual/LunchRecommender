package com.sparta.lunchrecommender.domain.comment.entity;

import com.sparta.lunchrecommender.domain.comment.dto.CommentRequestDto;
import com.sparta.lunchrecommender.domain.post.entity.Post;
import com.sparta.lunchrecommender.domain.user.entity.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class CommentTest {

    @Mock
    Post post;
    @Mock
    User user;

    @Test
    @DisplayName("Comment 생성자 테스트")
    void commentConstructorTest() {
        // given
        Long commentId = 1L;
        Long likeCount = 10L;
        Post post = new Post();
        post.setPostId(1L);
        User user = new User();
        user.setUserId(2L);
        CommentRequestDto commentRequestDto = new CommentRequestDto("testContent1");

        // when
        Comment comment = new Comment(commentRequestDto, post, user);
        comment.setCommentId(commentId);
        comment.setLikeCount(likeCount);

        // then
        assertThat(comment.getCommentId()).isEqualTo(commentId);
        assertThat(comment.getContent()).isEqualTo(commentRequestDto.getContent());
        assertThat(comment.getLikeCount()).isEqualTo(likeCount);
        assertThat(comment.getPost()).isEqualTo(post);
        assertThat(comment.getUser()).isEqualTo(user);
    }

    @Test
    @DisplayName("Comment Update 테스트")
    void commentUpdateTest() {
        CommentRequestDto commentRequestDto = new CommentRequestDto("testContent2");
        Comment comment = new Comment();

        // when
        comment.update(commentRequestDto);

        // then
        assertThat(comment.getContent()).isEqualTo(commentRequestDto.getContent());
    }


    @Test
    @DisplayName("Comment 무결성 검사")
    void commentValidTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        String content1 = null;
        String content2 = "";
        String content3 = " ";
        Comment comment = new Comment();

        // when
        comment.setContent(content1);
        Set<ConstraintViolation<Comment>> violations1 = validator.validate(comment);
        comment.setContent(content2);
        Set<ConstraintViolation<Comment>> violations2 = validator.validate(comment);
        comment.setContent(content3);
        Set<ConstraintViolation<Comment>> violations3 = validator.validate(comment);

        // then
        assertEquals(violations1.iterator().next().getMessage(),"내용을 입력해주세요");
        assertEquals(violations2.iterator().next().getMessage(),"내용을 입력해주세요");
        assertEquals(violations3.iterator().next().getMessage(),"내용을 입력해주세요");
    }

}