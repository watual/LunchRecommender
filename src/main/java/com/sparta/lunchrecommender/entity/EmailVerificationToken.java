package com.sparta.lunchrecommender.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Date expiryDate;


    public EmailVerificationToken(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
        this.expiryDate = calculateExpiryDate();
    }

    // 토큰 만료일 계산 메소드
    private Date calculateExpiryDate() {
        // 토큰 만료 시간 설정 (24시간)
        long expiryTime = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
        return new Date(expiryTime);
    }
}
