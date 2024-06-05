package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.dto.profile.ProfileRequestDto;
import com.sparta.lunchrecommender.dto.profile.ProfileResponseDto;
import com.sparta.lunchrecommender.entity.TmpUserSM;
import com.sparta.lunchrecommender.repository.TmpUserSMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final TmpUserSMRepository repository;

    // 프로필조회
    public ProfileResponseDto getProfile() {

         TmpUserSM tmpUser = repository.findById(1L).orElseThrow(
                 () -> new IllegalArgumentException("해당하는 유저가 없습니다.")
         );

         return new ProfileResponseDto(tmpUser);
    }

    // 프로필수정
    @Transactional
    public ProfileResponseDto updateProfile(ProfileRequestDto requestDto) {

        TmpUserSM tmpUser = repository.findById(1L).orElseThrow(
                () -> new IllegalArgumentException("해당하는 유저가 없습니다.")
        );
        tmpUser.update(requestDto);

        return new ProfileResponseDto(tmpUser);
    }
}
