package com.sparta.lunchrecommender.domain.post.entity;

import com.sparta.lunchrecommender.global.util.Timestamped;
import com.sparta.lunchrecommender.domain.user.entity.User;
import com.sparta.lunchrecommender.domain.post.dto.PostCreateRequestDto;
import com.sparta.lunchrecommender.domain.post.dto.PostUpdateRequestDto;
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
    private Long likeCount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    public Post(PostCreateRequestDto requestDto) {
        this.content = requestDto.getContent();
    }

    public void update(PostUpdateRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
