package com.proxy;

import org.springframework.stereotype.Component;

@Component
public class DiyLog {
    public void methodBefore() {
        System.out.println("方式执行前");
    }

    public void methodAfter() {
        System.out.println("方法执行后");
    }
}
