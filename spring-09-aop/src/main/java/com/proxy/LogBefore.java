package com.proxy;


import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class LogBefore implements MethodBeforeAdvice {

    //method : 要执行的目标对象的方法
    //objects : 被调用的方法的参数
    //Object : 目标对象
    @Override
    public void before(Method method, Object[] arg, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + "的" + method.getName() + "准备开始执行了！");
    }
}
