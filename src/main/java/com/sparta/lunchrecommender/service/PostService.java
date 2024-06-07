package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.dto.post.PostCreateRequestDto;
import com.sparta.lunchrecommender.dto.post.PostResponseDto;
import com.sparta.lunchrecommender.dto.post.PostUpdateRequestDto;
import com.sparta.lunchrecommender.entity.Post;
import com.sparta.lunchrecommender.entity.User;
import com.sparta.lunchrecommender.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostResponseDto> getPosts() {

        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new).toList();

    }

    public void createPost(PostCreateRequestDto requestDto, User user) {

        Post post = new Post(requestDto);

        post.setUser(user);

        Post savedPost = postRepository.save(post);

        new PostResponseDto(savedPost);
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto) {

        Post post = findPostById(postId);

        post.update(requestDto);

        return new PostResponseDto(post);
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(()->
                new IllegalArgumentException("선택한 게시물이 존재하지 않습니다."));
    }

    public void deletePost(Long postId) {

        Post post = findPostById(postId);

        postRepository.delete(post);

    }
}
