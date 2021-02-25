package com.demo02.proxy;

import com.demo02.pojo.User;
import com.demo02.pojo.UserImpl;

import java.text.MessageFormat;

public class AddLogProxy implements UserImpl {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addLog(String methodName) {
        System.out.println("[Debug]日志:" + methodName + "用户");
        System.out.println(MessageFormat.format("[Debug]日志1:{0}用户", methodName));
    }

    @Override
    public void insert() {
        user.insert();
        addLog("insert");
    }

    @Override
    public void update() {
        user.update();
        addLog("update");
    }

    @Override
    public void select() {
        user.select();
        addLog("select");
    }

    @Override
    public void delete() {
        user.delete();
        addLog("delete");
    }
}
