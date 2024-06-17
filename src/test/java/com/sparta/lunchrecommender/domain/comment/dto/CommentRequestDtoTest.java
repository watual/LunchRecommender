package com.sparta.lunchrecommender.domain.comment.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommentRequestDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("CommentRequestDto content 데이터 테스트")
    void commentRequestDtoTest() {
        //
        String content = "testContent";
        CommentRequestDto commentRequestDto = new CommentRequestDto(content);
        Field[] fields = commentRequestDto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                System.out.println(field.getName() + " : " + field.get(commentRequestDto));
            }catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        assertThat(commentRequestDto).isNotNull();
        assertEquals(commentRequestDto.getContent(),content);
    }

    @Test
    @DisplayName("CommentRequestDto content NotBlank 검증 테스트1")
    void commentRequestDtoValidTest1() {
        // given
        String content1 = null;
        String content2 = "";
        String content3 = " ";
        // when
        Set<ConstraintViolation<CommentRequestDto>> violations1 = validator.validate(new CommentRequestDto(content1));
        Set<ConstraintViolation<CommentRequestDto>> violations2 = validator.validate(new CommentRequestDto(content2));
        Set<ConstraintViolation<CommentRequestDto>> violations3 = validator.validate(new CommentRequestDto(content3));
        // then
        System.out.println(violations1);
        System.out.println(violations2);
        System.out.println(violations3);
        assertEquals(violations1.iterator().next().getMessage(),"공백일 수 없습니다");
        assertEquals(violations2.iterator().next().getMessage(),"공백일 수 없습니다");
        assertEquals(violations3.iterator().next().getMessage(),"공백일 수 없습니다");
    }
}
