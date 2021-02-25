package com.study.demo01.pojo;

public class User {
    String  name;

//    public User() {
//        System.out.println("这是无参构建函数～");
//    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
