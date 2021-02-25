package com.demo02.pojo;

public class User implements UserImpl {

    @Override
    public void insert() {
        System.out.println("增加用户");
    }

    @Override
    public void update() {
        System.out.println("修改用户");
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
