package com.study.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public UserDao() {
        System.out.println("这是UserDao的无参构造函数！");
    }
}
