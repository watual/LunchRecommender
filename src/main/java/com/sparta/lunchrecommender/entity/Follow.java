package com.sparta.lunchrecommender.entity;

import com.sparta.lunchrecommender.dto.follow.FollowRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "followed")
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long follow_id;

    private Long userId; // 나
    private Long followeeId; // 팔로우 할 계정

    public Follow(FollowRequestDto request){
        this.userId = request.getUserId();
        this.followeeId = request.getFollowId();
    }
}
