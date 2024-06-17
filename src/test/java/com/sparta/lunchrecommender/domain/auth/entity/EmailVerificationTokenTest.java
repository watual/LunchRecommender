package com.sparta.lunchrecommender.domain.auth.entity;

import com.sparta.lunchrecommender.domain.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailVerificationTokenTest {
    // given
    @Mock
    private User mockUser;
    private EmailVerificationToken token;

    @BeforeEach
    void setUp() {
        // when
        // 새로운 EmailVerificationToken 객체를 생성합니다.
        token = new EmailVerificationToken(mockUser);
    }

    @Test
    @DisplayName("메일확인토큰 생성 테스트")
    void tokenGenerateTest() {
        // then: 토큰이 생성되었는지 확인합니다.
        assertThat(token.getToken()).isNotNull();
        assertThat(token.getToken()).hasSize(UUID.randomUUID().toString().length());
    }

    @Test
    @DisplayName("메일확인토큰 사용자 연결 테스트")
    void tokenUserConnectionTest() {
        // then: 토큰이 올바른 사용자와 연결되었는지 확인합니다.
        assertThat(token.getUser()).isEqualTo(mockUser);
    }

    @Test
    @DisplayName("메일확인토큰 만료기한 확인 테스트")
    void tokenExpiryTimeTest() {
        // then: 만료 시간이 현재 시간에서 정확히 180초 후로 설정되었는지 확인합니다.
        LocalDateTime expectedExpiryTime = LocalDateTime.now().plusSeconds(180);
        long secondsDifference = ChronoUnit.SECONDS.between(expectedExpiryTime, token.getExpiresAt());
        assertThat(secondsDifference).isBetween(-5L, 5L); // 5초 이내의 차이만 허용
    }
}