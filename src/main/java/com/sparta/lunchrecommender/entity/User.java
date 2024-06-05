package com.sparta.lunchrecommender.entity;

import com.sparta.lunchrecommender.constant.UserStatus;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends UserTimestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column
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
    private String refresh_token;
    @Column
    private String status;

    public void setStatus(UserStatus status) {
        if(!status.getStatus().equals(this.status)) {
            this.status = status.getStatus();
            this.setStatusModifiedAt(LocalDateTime.now());
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
        this.setStatusModifiedAt(LocalDateTime.now());
    }
}
