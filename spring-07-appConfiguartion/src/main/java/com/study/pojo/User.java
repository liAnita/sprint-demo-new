package com.study.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component表示这个类被spring接管，将User注册到容器中
@Component
public class User {

    @Value("小可爱")
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
