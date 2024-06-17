package com.sparta.lunchrecommender.global.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Component
@Aspect
@RequiredArgsConstructor
public class LogPrint {

    @Pointcut("execution(* com.sparta.lunchrecommender.domain.*.controller.*(..))")
    private void log() {}

    @Before("log()")
    public void execute(JoinPoint joinPoint) {
        // RequestContextHolder를 사용하여 현재 요청 정보를 가져옵니다
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            // Request URL과 HTTP Method를 로깅합니다
            String url = request.getRequestURL().toString();
            String httpMethod = request.getMethod();

            log.info("Request URL: {}, HTTP Method: {}", url, httpMethod);
        }
    }
}
