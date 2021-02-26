package com.proxy;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect //定义切面就是annotationPointcut
//切面（ASPECT）：横切关注点 被模块化 的特殊对象。即，它是一个类。
public class AnnotationPointcut {

    //目标（Target）：UserService，被通知对象。
    //切入点（PointCut）：切面通知 执行的 “地点”的定义。
    @Before("execution(* com.service.UserService.*(..))")
    //通知（Advice）：切面必须要完成的工作。即，它是类中的一个方法。
    public void methodBefore() {
        System.out.println("方法执行前");
    }

    @AfterReturning("execution(* com.service.UserService.*(..))")
    public void methodAfter() {
        System.out.println("方法执行后");
    }
}
