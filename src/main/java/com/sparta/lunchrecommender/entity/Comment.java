package com.sparta.lunchrecommender.entity;

import com.sparta.lunchrecommender.dto.comment.CommentRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @NotBlank(message = "내용을 입력해주세요.")
    @Column(nullable = false)
    private String content;


//    @Column(nullable = false)
//    private Long like_count;

    // 게시물 id

    // 사용자 pk

    public Comment(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
        // this.like_count = like_count;
    }
}
