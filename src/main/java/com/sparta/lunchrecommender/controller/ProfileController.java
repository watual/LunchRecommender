package com.sparta.lunchrecommender.controller;


import com.sparta.lunchrecommender.dto.profile.ProfileRequestDto;
import com.sparta.lunchrecommender.dto.profile.ProfileResponseDto;
import com.sparta.lunchrecommender.security.UserDetailsImpl;
import com.sparta.lunchrecommender.service.ProfileService;
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
