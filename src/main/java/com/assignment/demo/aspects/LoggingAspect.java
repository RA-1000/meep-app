package com.assignment.demo.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.assignment.demo.controllers.*.*(..)) "
            + "|| execution(* com.assignment.demo.services.*.*(..)) "
            + "|| execution(* com.assignment.demo.repositories.impl.*.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* com.assignment.demo.controllers.*.*(..)) "
            + "|| execution(* com.assignment.demo.services.*.*(..)) "
            + "|| execution(* com.assignment.demo.repositories.impl.*.*(..))")
    public void logServiceExit(JoinPoint joinPoint) {
        log.info("Exit: {}.{}()", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.assignment.demo.exceptions.*.*(..)))", throwing = "ex")
    public void logServiceAccessException(JoinPoint joinPoint, Exception ex) throws Throwable {
        log.error("Error in: {}.{}()", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), ex);
    }

}
