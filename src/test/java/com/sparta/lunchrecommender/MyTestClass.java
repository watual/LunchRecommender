package com.sparta.lunchrecommender;

import com.sparta.lunchrecommender.domain.post.entity.Post;
import com.sparta.lunchrecommender.domain.user.controller.UserController;
import com.sparta.lunchrecommender.domain.user.dto.LoginRequestDto;
import com.sparta.lunchrecommender.domain.user.dto.UserRequestDto;
import com.sparta.lunchrecommender.domain.user.entity.User;
import com.sparta.lunchrecommender.domain.user.repository.UserRepository;
import com.sparta.lunchrecommender.domain.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("서비스 테스트")
public class MyTestClass {

//    @Mock
//    UserRepository userRepository;
//
//    @InjectMocks
//    UserService userService;
//
//    @BeforeEach
//    public void beforeEach() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    @DisplayName("유저 로그인 확인")
    void myTest() {

        System.out.println("MyTestClass.myTest");
        // Given
//        userRepository.findByLoginId("test").orElseThrow();
//
//        given(userService.signup(Mockito.any(UserRequestDto.class))).willReturn("https://localhost:443/api/auth/confirm?token=aaa");
//
//        // 테스트 코드 작성
//        Assertions.assertEquals("제목 테스트1", "제목 테스트1");
//        Assertions.assertEquals("내용 테스트1", "내용 테스트1");
//        Assertions.assertEquals("매니저테스트1@gmail.com", "매니저테스트1@gmail.com");
    }
}
