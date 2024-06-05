package com.sparta.lunchrecommender.service;

import com.sparta.lunchrecommender.dto.profile.ProfileResponseDto;
import com.sparta.lunchrecommender.entity.TmpUserSM;
import com.sparta.lunchrecommender.repository.TmpUserSMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final TmpUserSMRepository repository;

    public ProfileResponseDto getProfile() {

         TmpUserSM tmpUser = repository.findById(1L).orElseThrow(
                 () -> new IllegalArgumentException("해당하는 유저가 없습니다.")
         );

         return new ProfileResponseDto(tmpUser);
    }
}
