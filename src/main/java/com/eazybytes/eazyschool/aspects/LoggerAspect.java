package com.eazybytes.eazyschool.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
    @Around("execution(* com.eazybytes.eazyschool..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(joinPoint.getSignature().toString() +" method execution starts");
        Instant start = Instant.now();
        Object result = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeelapsed = Duration.between(start, finish).toMillis();
        log.info("Time took to execute "+joinPoint.getSignature().toString()+" method: "+timeelapsed +" ms");
        log.info(joinPoint.getSignature().toString() +" method execution ends");
        return result;
    }
    @AfterThrowing(value="execution(* com.eazybytes.eazyschool..*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature().toString() +" an exception occured "+ex.getMessage());
    }
}
