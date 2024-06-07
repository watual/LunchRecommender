package com.sparta.lunchrecommender.security;


import com.sparta.lunchrecommender.constant.UserStatus;
import com.sparta.lunchrecommender.entity.User;
import com.sparta.lunchrecommender.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        // 사용자 객체 검색
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + loginId));
        //
        if(user.getStatus().equals(UserStatus.DELETED.getStatus())){
            throw new UsernameNotFoundException("User Deleted" + loginId);
        }

        return new UserDetailsImpl(user);
    }
}