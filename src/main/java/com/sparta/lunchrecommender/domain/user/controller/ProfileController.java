package com.sparta.lunchrecommender.domain.user.controller;


import com.sparta.lunchrecommender.domain.user.dto.ProfileRequestDto;
import com.sparta.lunchrecommender.domain.user.dto.ProfileResponseDto;
import com.sparta.lunchrecommender.global.security.UserDetailsImpl;
import com.sparta.lunchrecommender.domain.user.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ProfileResponseDto getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){ //프로필조회
        return profileService.getProfile(userDetails);
    }

    @PatchMapping
    public ProfileResponseDto updateProfile(@RequestBody ProfileRequestDto profileRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return profileService.updateProfile(profileRequestDto,userDetails);
    }

}
