package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.constant.UserStatus;
import com.sparta.lunchrecommender.dto.user.PasswordRequestDto;
import com.sparta.lunchrecommender.dto.user.UserRequestDto;
import com.sparta.lunchrecommender.entity.User;
import com.sparta.lunchrecommender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;

@Slf4j
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
        // 이메일, 아이디 중복검사 >
        User user = new User(
                requestDto.getLoginId(),
                password,
                requestDto.getName(),
                requestDto.getNickname(),
                requestDto.getEmail(),
                requestDto.getIntro(),
                UserStatus.ACTIVE
                );
        registerUser(user);
    }

    // DB 값 중복 입력 시 오류 처리
    public void registerUser(User user) {
        try{
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            String errorMessage = Objects.requireNonNull(ex.getRootCause()).getMessage();
            if (errorMessage.contains("Duplicate entry")) {
                if (errorMessage.contains("login_id")) {
                    throw new IllegalArgumentException("ID already exists");
                }
            }
            throw new IllegalArgumentException("An unknown error occurred");
        }
    }

    @Transactional
    public void deleteAccount(String loginId, PasswordRequestDto passwordRequestDto) {
        log.info("deleteAccount");
        User user = userRepository.findByLoginId(loginId).orElseThrow();
        if(!passwordEncoder.matches(passwordRequestDto.getPassword(), user.getPassword())) {
            log.info("회원탈퇴 취소");
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다. 회원탈퇴를 취소합니다.");
        }
        user.setStatus(UserStatus.DELETED);
    }
}