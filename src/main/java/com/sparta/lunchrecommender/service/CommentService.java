package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.dto.comment.CommentRequestDto;
import com.sparta.lunchrecommender.dto.comment.CommentResponseDto;
import com.sparta.lunchrecommender.entity.Comment;
import com.sparta.lunchrecommender.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;



    public CommentResponseDto addComment(Long postId, CommentRequestDto commentRequestDto) {
//        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("선택한 게시물이 존재하지 않습니다."));
//        Comment comment = new Comment(commentRequestDto, post, userDetails.getUser());
//        post.addCommentList(comment); // post 객체에 댓글 추가

        Comment comment = commentRepository.save(new Comment(commentRequestDto));

        return new CommentResponseDto(comment);
    }
}
