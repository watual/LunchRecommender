package com.sparta.lunchrecommender.controller;


import com.sparta.lunchrecommender.dto.profile.ProfileResponseDto;
import com.sparta.lunchrecommender.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ProfileResponseDto getProfile(){ //프로필조회
        return profileService.getProfile();
    }


}
