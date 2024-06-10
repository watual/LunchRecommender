package com.sparta.lunchrecommender.domain.user.controller;


import com.sparta.lunchrecommender.domain.user.dto.ProfileRequestDto;
import com.sparta.lunchrecommender.domain.user.dto.ProfileResponseDto;
import com.sparta.lunchrecommender.global.dto.HttpResponseDto;
import com.sparta.lunchrecommender.global.security.UserDetailsImpl;
import com.sparta.lunchrecommender.domain.user.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<HttpResponseDto> getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){ //프로필조
        return ResponseEntity.status(HttpStatus.OK).body(
                HttpResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("프로필 조회가 완료되었습니다.")
                        .data(profileService.getProfile(userDetails))
                        .build()
        );
    }

    @PatchMapping
    public ResponseEntity<HttpResponseDto> updateProfile(@RequestBody ProfileRequestDto profileRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.status(HttpStatus.OK).body(
                HttpResponseDto.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("프로필 수정이 완료되었습니다.")
                        .data(profileService.updateProfile(profileRequestDto,userDetails))
                        .build()
        );
    }

}
