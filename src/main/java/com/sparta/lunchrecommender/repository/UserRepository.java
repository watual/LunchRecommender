package com.sparta.lunchrecommender.repository;

import com.sparta.lunchrecommender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
