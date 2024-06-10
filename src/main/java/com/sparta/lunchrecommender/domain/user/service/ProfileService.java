package com.sparta.lunchrecommender.domain.user.service;

import com.sparta.lunchrecommender.domain.user.dto.ProfileRequestDto;
import com.sparta.lunchrecommender.domain.user.dto.ProfileResponseDto;
import com.sparta.lunchrecommender.domain.user.entity.User;
import com.sparta.lunchrecommender.domain.user.repository.UserRepository;
import com.sparta.lunchrecommender.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository repository;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // 프로필조회
    public ProfileResponseDto getProfile(UserDetailsImpl userDetails) {

         User user = repository.findById(userDetails.getUser().getUserId()).orElseThrow(
                 () -> new IllegalArgumentException("해당하는 유저가 없습니다.")
         );

         return new ProfileResponseDto(user);
    }

    // 프로필수정
    @Transactional
    public ProfileResponseDto updateProfile(ProfileRequestDto requestDto,
                                            UserDetailsImpl userDetails) {

        User user = repository.findById(userDetails.getUser().getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당하는 유저가 없습니다.")
        );
        //비밀번호 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        requestDto.setPassword(password);

        user.update(requestDto);

        return new ProfileResponseDto(user);
    }
}
