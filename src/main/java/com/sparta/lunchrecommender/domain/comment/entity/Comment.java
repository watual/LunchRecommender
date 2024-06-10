package com.sparta.lunchrecommender.domain.comment.entity;

import com.sparta.lunchrecommender.domain.comment.dto.CommentRequestDto;
import com.sparta.lunchrecommender.global.util.Timestamped;
import com.sparta.lunchrecommender.domain.user.entity.User;
import com.sparta.lunchrecommender.domain.post.entity.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @NotBlank(message = "내용을 입력해주세요.")
    @Column(nullable = false)
    private String content;


    @Column
    private Long likeCount;

    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    public Comment(CommentRequestDto commentRequestDto, Post post, User user) {
        this.content = commentRequestDto.getContent();
        this.post = post;
        this.user = user;
        this.likeCount = 0L;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }
}
