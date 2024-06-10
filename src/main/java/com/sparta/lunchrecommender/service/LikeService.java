package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.constant.ContentsTypeEnum;
import com.sparta.lunchrecommender.dto.HttpResponseDto;
import com.sparta.lunchrecommender.dto.like.LikeRequestDto;
import com.sparta.lunchrecommender.dto.like.LikeResponseDto;
import com.sparta.lunchrecommender.entity.Comment;
import com.sparta.lunchrecommender.entity.Like;
import com.sparta.lunchrecommender.entity.Post;
import com.sparta.lunchrecommender.repository.CommentRepository;
import com.sparta.lunchrecommender.repository.LikeRepository;
import com.sparta.lunchrecommender.repository.PostRepository;
import com.sparta.lunchrecommender.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    @Transactional
    public ResponseEntity<HttpResponseDto> likeCreate(LikeRequestDto likeRequestDto, UserDetailsImpl userDetails) {

        boolean isPost = likeRequestDto.getContentsType().equals(ContentsTypeEnum.POST);

        if (isPost) { //게시글
            Post post = postRepository.findById(likeRequestDto.getContentId()).orElseThrow(
                    () -> new NullPointerException("존재하지 않는 게시글입니다.")
            );
            //좋아요를 이미 한 게시글인지?
            if (likeRepository.findByUserIdAndContentsTypeAndContentId
                    (userDetails.getUser().getUserId(), ContentsTypeEnum.POST, likeRequestDto.getContentId()) != null) {
                throw new NullPointerException("이미 좋아요를 누르셨습니다.");
            }
            Long likecount = post.getLike_count() + 1;
            post.setLikeCount(likecount);

            Like like = new Like(likeRequestDto);
            like.setUserId(userDetails.getUser().getUserId());
            likeRepository.save(like);


        } else { //댓글
            Comment comment = commentRepository.findById(likeRequestDto.getContentId()).orElseThrow(
                    () -> new NullPointerException("존재하지 않는 댓글입니다.")
            );

            //좋아요를 이미 한 댓글인지?
            if (likeRepository.findByUserIdAndContentsTypeAndContentId
                    (userDetails.getUser().getUserId(), ContentsTypeEnum.COMMENT, likeRequestDto.getContentId()) != null) {
                throw new NullPointerException("이미 좋아요를 누르셨습니다.");
            }

            Long likecount = comment.getLike_count() + 1;
            comment.setLikeCount(likecount);

            Like like = new Like(likeRequestDto);
            like.setUserId(userDetails.getUser().getUserId());
            likeRepository.save(like);
        }

        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "좋아요성공"), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<HttpResponseDto> likeCancel(LikeRequestDto likeRequestDto, UserDetailsImpl userDetails) {

        boolean isPost = likeRequestDto.getContentsType().equals(ContentsTypeEnum.POST);
        if (isPost) {
            Post post = postRepository.findById(likeRequestDto.getContentId()).orElseThrow(
                    () -> new NullPointerException("존재하지 않는 게시글입니다.")
            );
            Like like = likeRepository.findByUserIdAndContentsTypeAndContentId(userDetails.getUser().getUserId(), ContentsTypeEnum.POST, likeRequestDto.getContentId());
            if (like == null) {
                throw new NullPointerException("데이터가 존재하지 않습니다.");
            }
            if (!like.getUserId().equals(userDetails.getUser().getUserId())) {
                throw new IllegalArgumentException("유저 정보가 올바르지 않습니다.");
            }

            var likecount = post.getLike_count() - 1;
            post.setLikeCount(likecount);

            likeRepository.delete(like);

        } else {
            Comment comment = commentRepository.findById(likeRequestDto.getContentId()).orElseThrow(
                    () -> new NullPointerException("존재하지 않는 댓글입니다.")
            );
            Like like = likeRepository.findByUserIdAndContentsTypeAndContentId(userDetails.getUser().getUserId(), ContentsTypeEnum.COMMENT, likeRequestDto.getContentId());
            if (like == null) {
                throw new NullPointerException("데이터가 존재하지 않습니다.");
            }
            if (!like.getUserId().equals(userDetails.getUser().getUserId())) {
                throw new IllegalArgumentException("유저 정보가 올바르지 않습니다.");
            }

            var likecount = comment.getLike_count() - 1;
            comment.setLikeCount(likecount);

            likeRepository.delete(like);
        }


        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "좋아요취소"), HttpStatus.OK);
    }

    @Transactional
    public LikeResponseDto likeCount(LikeRequestDto likeRequestDto) {

        boolean isPost = likeRequestDto.getContentsType().equals(ContentsTypeEnum.POST);
        if (isPost) {
            Post post = postRepository.findById(likeRequestDto.getContentId()).orElseThrow(
                    () -> new NullPointerException("존재하지 않는 게시글입니다.")
            );

            return new LikeResponseDto(ContentsTypeEnum.POST,post.getPostId(),post.getLike_count());
        }
        else{
            Comment comment = commentRepository.findById(likeRequestDto.getContentId()).orElseThrow(
                    () -> new NullPointerException("존재하지 않는 댓글입니다.")
            );
            return new LikeResponseDto(ContentsTypeEnum.COMMENT,comment.getCommentId(),comment.getLike_count());
        }
    }
}
