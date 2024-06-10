package com.sparta.lunchrecommender.domain.user.entity;

import com.sparta.lunchrecommender.global.util.Timestamped;
import com.sparta.lunchrecommender.domain.user.constant.UserStatus;
import com.sparta.lunchrecommender.domain.user.dto.ProfileRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, unique = true)
    private String loginId;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String nickname;
    @Column
    private String email;
    @Column
    private String intro;
    @Column
    @Setter
    private String refresh_token;
    @Column
    private String status;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime statusModifiedAt;

//    @OneToMany(mappedBy = "User")
//    private List<Post> posts = new ArrayList<>();

    public void setStatus(UserStatus status) {
        if(!status.getStatus().equals(this.status)) {
            this.status = status.getStatus();
            this.statusModifiedAt = LocalDateTime.now();
        }
    }
    public User(String loginId, String password, String name, String nickname, String email, String intro, UserStatus status) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.intro = intro;
        this.refresh_token = refresh_token;
        this.status = status.getStatus();
        this.statusModifiedAt = LocalDateTime.now();
    }

    // 프로필 수정 추가
    public void update(ProfileRequestDto requestDto){
        this.password = requestDto.getPassword();
        this.name = requestDto.getName();
        this.nickname = requestDto.getNickname();
        this.intro = requestDto.getIntro();
        this.email = requestDto.getEmail();
    }
}
