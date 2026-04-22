package com.cdac.aspects;

import java.util.Arrays;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {



 @Before("execution(* com.cdac.controller.*.*(..))")
    public void logBefore(JoinPoint jp) {
        System.out.println("hiiihi");
        log.info(">>> Calling: {} | Args: {}", 
            jp.getSignature().getName(), 
            Arrays.toString(jp.getArgs()));
    }
    
}