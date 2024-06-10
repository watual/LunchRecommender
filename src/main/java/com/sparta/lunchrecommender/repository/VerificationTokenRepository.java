package com.sparta.lunchrecommender.repository;

import com.sparta.lunchrecommender.entity.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {
    EmailVerificationToken findByToken(String token);
}
