package com.sparta.lunchrecommender.entity;

import com.sparta.lunchrecommender.dto.post.PostCreateRequestDto;
import com.sparta.lunchrecommender.dto.post.PostUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "Post")
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column
    private String loginId;

    @Column
    private String nickname;

    @Column(nullable = false)
    private String content;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private TmpUser tmpUser;

    public Post(PostCreateRequestDto requestDto) {
        this.content = requestDto.getContent();
    }

    public void update(PostUpdateRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
