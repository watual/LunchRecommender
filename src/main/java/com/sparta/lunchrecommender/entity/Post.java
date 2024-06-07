package com.sparta.lunchrecommender.entity;

import com.sparta.lunchrecommender.dto.post.PostCreateRequestDto;
import com.sparta.lunchrecommender.dto.post.PostUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Post")
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;


    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long like_count;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    public Post(PostCreateRequestDto requestDto) {
        this.content = requestDto.getContent();
    }

    public void update(PostUpdateRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
    public void setLikeCount(Long likeCount) {
        this.like_count = likeCount;
    }

}
