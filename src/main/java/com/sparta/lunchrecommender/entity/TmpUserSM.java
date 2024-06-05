package com.sparta.lunchrecommender.entity;

import com.sparta.lunchrecommender.dto.profile.ProfileRequestDto;
import com.sparta.lunchrecommender.dto.profile.ProfileResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_sm")
public class TmpUserSM extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String loginid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String intro;

    @Column(nullable = false)
    private String email;
//    private String userstat;
//    private String refreshtoken;
//    private String statmodify_at;


    public TmpUserSM(String loginid, String password, String username, String nickname,String intro, String email) {
        this.loginid = loginid;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.intro = intro;
        this.email = email;
    }


    //프로필 수정
    public void update(String password,String username,String nickname,String intro,String email){
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.intro = intro;
        this.email = email;

    }

    public void update(ProfileRequestDto requestDto){
        this.password = requestDto.getPassword();
        this.username = requestDto.getUsername();
        this.nickname = requestDto.getNickname();
        this.intro = requestDto.getIntro();
        this.email = requestDto.getEmail();

    }
}
