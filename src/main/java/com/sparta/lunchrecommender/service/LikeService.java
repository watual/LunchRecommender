package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.constant.ContentsTypeEnum;
import com.sparta.lunchrecommender.dto.HttpResponseDto;
import com.sparta.lunchrecommender.dto.like.LikeRequestDto;
import com.sparta.lunchrecommender.entity.Like;
import com.sparta.lunchrecommender.repository.CommentRepository;
import com.sparta.lunchrecommender.repository.LikeRepository;
import com.sparta.lunchrecommender.repository.PostRepository;
import com.sparta.lunchrecommender.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    LikeRepository likeRepository;
    PostRepository postRepository;
    CommentRepository commentRepository;

    public ResponseEntity<HttpResponseDto> likeCreate(LikeRequestDto likeRequestDto, UserDetailsImpl userDetails) {

        if (likeRequestDto.getContentsType().equals(ContentsTypeEnum.POST)) { //게시글
            var post = postRepository.findById(likeRequestDto.getContentId()).orElseThrow(
                    () -> new NullPointerException("존재하지 않는 게시글입니다.")
            );

            if (likeRequestDto.getUserId().equals(userDetails.getUser().getUserId())) {
                throw new IllegalArgumentException("이미 좋아요 한 글입니다.");
            }
        } else { //댓글
            var comment = commentRepository.findById(likeRequestDto.getContentId()).orElseThrow(
                    () -> new NullPointerException("존재하지 않는 댓글입니다.")
            );

            if (likeRequestDto.getUserId().equals(userDetails.getUser().getUserId())) {
                throw new IllegalArgumentException("이미 좋아요 한 댓글입니다.");
            }
        }

        Like like = new Like(likeRequestDto);
        likeRepository.save(like);

        return new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, "좋아요성공"), HttpStatus.OK);
    }

}
