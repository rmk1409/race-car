package com.veselov.alex.racecar.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonJoinPoints {
    /**
     * Create pointcut for package
     */
    @Pointcut("execution(public * com.veselov.alex.racecar..*(..))")
    public void racecarPackage() {
    }

    /**
     * Create pointcut for getter methods
     */
    @Pointcut("execution(* com.veselov.alex.racecar..get*(..))")
    public void getter() {
    }

    /**
     * Create pointcut for setter methods
     */
    @Pointcut("execution(* com.veselov.alex.racecar..set*(..))")
    public void setter() {
    }

    /**
     * Create pointcut: include package ... exclude getter/setter
     */
    @Pointcut("racecarPackage() && !(getter() || setter())")
    public void racecarPackageNoGetterSetter() {
    }
}
