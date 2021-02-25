package com.demo04;

public class Client {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        ProxyFactory proxyFactory = new ProxyFactory(userDao);
        UserDao proxyInstance = (UserDao) proxyFactory.getProxyInstance();
        proxyInstance.save();
    }
}
