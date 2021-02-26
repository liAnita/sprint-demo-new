package com.service;

import com.dao.UserDao;
import org.springframework.stereotype.Component;

@Component
public class UserService implements UserDao {

    @Override
    public void add() {
        System.out.println("新增用户");
    }

    @Override
    public void update() {
        System.out.println("更新用户");
    }

    @Override
    public void select() {
        System.out.println("查询用户");
    }

    @Override
    public void delete() {
        System.out.println("删除用户");
    }
}
