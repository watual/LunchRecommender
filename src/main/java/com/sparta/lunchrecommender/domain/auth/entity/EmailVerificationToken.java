package com.sparta.lunchrecommender.domain.auth.entity;

import com.sparta.lunchrecommender.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class EmailVerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    private LocalDateTime expiresAt;


    public EmailVerificationToken(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
        this.expiresAt = LocalDateTime.now().plusSeconds(180);
    }
}
