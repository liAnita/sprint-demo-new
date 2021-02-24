package com.study.config;

import com.study.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
 这个类也会被Spring托管，注入到ioc容器中，因为它本身就是一个Component
@Configuration 表示这是一个配置类，等价于之前学习的beans.xml
*/
@Configuration
@ComponentScan("com.study")
@Import(AppConfig2.class)
public class AppConfig {

    /*
    1.注册一个Bean,相当于之前写的<bean>标签
    2.这个方法的名称相当于bean的id
    3.这个方法的属性相当于bean的class
     */
    @Bean
    public User getUser() {
        return new User(); //就是返回要注入到bean的对象
    }
}
