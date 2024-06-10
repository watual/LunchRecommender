package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.constant.UserStatus;
import com.sparta.lunchrecommender.dto.HttpResponseDto;
import com.sparta.lunchrecommender.dto.user.PasswordRequestDto;
import com.sparta.lunchrecommender.dto.user.UserRequestDto;
import com.sparta.lunchrecommender.entity.EmailVerificationToken;
import com.sparta.lunchrecommender.entity.User;
import com.sparta.lunchrecommender.repository.UserRepository;
import com.sparta.lunchrecommender.repository.VerificationTokenRepository;
import com.sparta.lunchrecommender.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserUtil userUtil;
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final VerificationTokenRepository verificationTokenRepository;
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
                UserStatus.UNAUTHORIZED
                );
        registerUser(user);

        EmailVerificationToken token = new EmailVerificationToken(user);
        verificationTokenRepository.save(token);
        String url = "http://localhost:8080/api/auth/confirm?token=" + token.getToken();
        sendSimpleMessage(user.getEmail(), "LunchRecommender 회원가입 메일 인증", "메일 인증을 받으려면 링크를 클릭하세요: " + url);
    }

    private void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

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

    @Transactional
    public void logout(Long userId) {
        User user = userUtil.userVerifyById(userId);
        user.setRefresh_token(null);
    }

    public ResponseEntity<HttpResponseDto> confirmEmailToken(String token) {
        EmailVerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        log.info(verificationToken.getUser().getUserId().toString() + " 사용자 메일 인증 시도");
        // 유효하지 않은 토큰
        if (verificationToken == null) {
            log.info("유효하지 않은 키");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HttpResponseDto(HttpStatus.BAD_REQUEST, "유효하지 않은 키 입니다"));
//                    new ResponseEntity<>(new HttpResponseDto(HttpStatus.OK, result), HttpStatus.OK);
        }
        // 이미 인증된 사용자
        User user = verificationToken.getUser();
        if (UserStatus.ACTIVE.getStatus().equals(user.getStatus())) {
            log.info("이미 인증된 계정");
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new HttpResponseDto(HttpStatus.ALREADY_REPORTED, "이미 인증된 계정입니다"));
        }
        // 토큰 인증
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
        // 인증 완료
        log.info("메일 정상 인증 완료");
        return ResponseEntity.status(HttpStatus.OK).body(new HttpResponseDto(HttpStatus.OK, "인증이 완료되었습니다"));
    }
}