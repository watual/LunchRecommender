package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.dto.post.PostCreateRequestDto;
import com.sparta.lunchrecommender.dto.post.PostResponseDto;
import com.sparta.lunchrecommender.entity.Post;
import com.sparta.lunchrecommender.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostResponseDto> getPosts() {

        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new).toList();

    }

    public PostResponseDto createPost(PostCreateRequestDto requestDto) {

        Post post = new Post(requestDto);

        Post savedPost = postRepository.save(post);

        return new PostResponseDto(savedPost);
    }
}
