package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.constant.UserStatus;
import com.sparta.lunchrecommender.dto.user.UserRequestDto;
import com.sparta.lunchrecommender.entity.User;
import com.sparta.lunchrecommender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void signup(UserRequestDto requestDto) {
        String password = passwordEncoder.encode(requestDto.getPassword());
        if(userRepository.findByLoginId(requestDto.getLoginId()).isPresent()){
            throw new IllegalArgumentException("이미 등록된 회원입니다.");
        }
        User user = new User(
                requestDto.getLoginId(),
                password,
                requestDto.getName(),
                requestDto.getNickname(),
                requestDto.getEmail(),
                requestDto.getIntro(),
                UserStatus.ACTIVE
                );
        userRepository.save(user);
    }

}