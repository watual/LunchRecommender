package com.sparta.lunchrecommender.repository;

import com.sparta.lunchrecommender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String login_id);
}
