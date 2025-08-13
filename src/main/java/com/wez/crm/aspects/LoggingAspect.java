package com.wez.crm.aspects;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect{

  private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

  @Pointcut("execution(* com.wez.crm.service..*(..))")
  public void serviceMethods() {}

  @Before("serviceMethods()")
  public void logBefore(JoinPoint joinPoint) {
    log.info("Entering method: {} with args: {}",
        joinPoint.getSignature().toShortString(),
        Arrays.toString(joinPoint.getArgs()));
  }

  @AfterReturning(value = "serviceMethods()", returning = "result")
  public void logAfter(JoinPoint joinPoint, Object result) {
    log.info("Exiting method: {} with result: {}",
        joinPoint.getSignature().toShortString(),
        result);
  }
}
