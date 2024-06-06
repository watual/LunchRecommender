package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.dto.comment.CommentRequestDto;
import com.sparta.lunchrecommender.dto.comment.CommentResponseDto;
import com.sparta.lunchrecommender.entity.Comment;
import com.sparta.lunchrecommender.entity.Post;
import com.sparta.lunchrecommender.entity.User;
import com.sparta.lunchrecommender.repository.CommentRepository;
import com.sparta.lunchrecommender.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Transactional
    public CommentResponseDto addComment(Long postId, CommentRequestDto commentRequestDto, User user) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다."));
        Comment comment = commentRepository.save(new Comment(commentRequestDto, post, user));

        return new CommentResponseDto(comment);
    }

    public CommentResponseDto findCommentById(Long postId, Long commentId) {
        postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("선택한 댓글이 존재하지 않습니다."));

        return new CommentResponseDto(comment);
    }

    public List<CommentResponseDto> findCommentAll(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다."));

        return commentRepository.findAllByPostId(post.getPostId()).stream().map(CommentResponseDto::new).toList();
    }
}