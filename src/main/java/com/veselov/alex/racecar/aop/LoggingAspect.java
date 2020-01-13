package com.veselov.alex.racecar.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Advice will be invoked before every public method in package exclude getters/setters
     *
     * @param point
     */
    @Before("com.veselov.alex.racecar.aop.CommonJoinPoints.racecarPackageNoGetterSetter()")
    public void beforeRunMethod(JoinPoint point) {
        log.info("Invoking method '{}', arguments '{}'"
                , point.getSignature(), Arrays.toString(point.getArgs()));
    }
}