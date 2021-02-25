package com.demo01.pojo;

import com.demo01.proxy.MyProxy;

public class Client {
    public static void main(String[] args) {
        Host host = new Host();
        MyProxy myProxy = new MyProxy(host);
        myProxy.rent();
    }
}