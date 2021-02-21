package com.study.service;

import com.study.dao.UserDao;

public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void show(){
        userDao.show();
    }

}
