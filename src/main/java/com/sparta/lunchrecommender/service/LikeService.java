package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.constant.ContentsTypeEnum;
import com.sparta.lunchrecommender.controller.HttpResponseDto;
import com.sparta.lunchrecommender.dto.like.LikeRequestDto;
import com.sparta.lunchrecommender.entity.Like;
import com.sparta.lunchrecommender.repository.CommentRepository;
import com.sparta.lunchrecommender.repository.LikeRepository;
import com.sparta.lunchrecommender.repository.PostRepository;
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

    public ResponseEntity<HttpResponseDto> likeCreate(LikeRequestDto likeRequestDto) {

        if(likeRequestDto.getContentsType().equals(ContentsTypeEnum.POST)) //게시글
        {
            var post = postRepository.findById(likeRequestDto.getContentId()).orElseThrow(
                    ()->new NullPointerException("존재하지 않는 게시글입니다.")
            );

            // 이미좋아요 했는지 확인
//            if(likeRequestDto.getUserId().equals())
        }
        else{ //댓글
            var comment = commentRepository.findById(likeRequestDto.getContentId()).orElseThrow(
                    ()->new NullPointerException("존재하지 않는 댓글입니다.")
            );

            // 이미좋아요 했는지 확인
//            if(likeRequestDto.getUserId())
        }



        Like like = new Like(likeRequestDto);
        likeRepository.save(like);

//        return new HttpResponseDto("좋아요 성공");

//        return ResponseEntity.status(HttpStatus.OK).body(commentResponseDto);
//
        return null;
    }

}
