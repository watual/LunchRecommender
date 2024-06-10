package com.sparta.lunchrecommender.util;


import com.sparta.lunchrecommender.constant.UserStatus;
import com.sparta.lunchrecommender.entity.User;
import com.sparta.lunchrecommender.error.exception.AuthorizationFailedException;
import com.sparta.lunchrecommender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Component;

@Slf4j(topic = "JwtUtil")
@Component
@RequiredArgsConstructor
public class UserUtil {
    private final UserRepository userRepository;

    public User userVerifyById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("등록되지 않은 계정입니다")
        );
        if(user.getStatus().equals(UserStatus.DELETED.getStatus())){
            throw new NullPointerException("삭제된 계정입니다");
        }
        return user;
    }

    public User userVerifyByLoginId(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(
                () -> new AuthorizationFailedException("등록되지 않은 계정입니다")
        );
        if(UserStatus.UNAUTHORIZED.getStatus().equals(user.getStatus())) {
            throw new AuthorizationFailedException("메일 인증이 필요합니다");
        }
        if(UserStatus.DELETED.getStatus().equals(user.getStatus())){
            throw new AuthorizationFailedException("삭제된 계정입니다");
        }
        return user;
    }
}