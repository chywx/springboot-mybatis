package com.ocean.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();


    @Pointcut("execution(* com.ocean.mvc.controller..*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        // 记录开始时间
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("陈海洋-URL : " + request.getRequestURL().toString());
        log.info("陈海洋-HTTP_METHOD : " + request.getMethod());
        log.info("陈海洋-IP : " + request.getRemoteAddr());
        log.info("陈海洋-CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("陈海洋-ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "log()")
    public void after(Object ret) {
        // 处理完请求，返回内容
        log.info("陈海洋-RESPONSE : " + ret);
        log.info("陈海洋-PEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }


}
