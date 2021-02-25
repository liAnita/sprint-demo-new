package com.demo02.pojo;

import com.demo02.proxy.AddLogProxy;

public class Client {
    public static void main(String[] args) {
        User user = new User();
        AddLogProxy addLogProxy = new AddLogProxy();
        addLogProxy.setUser(user);
        addLogProxy.delete();
    }
}

