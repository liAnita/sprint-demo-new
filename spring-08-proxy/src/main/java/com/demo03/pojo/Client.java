package com.demo03.pojo;

import com.demo02.pojo.User;
import com.demo02.pojo.UserImpl;
import com.demo03.proxy.ProxyInvocationHandle;

public class Client {
    public static void main(String[] args) {
//        Rent host = new Host();
//        ProxyInvocationHandle proxyInvocationHandle = new ProxyInvocationHandle();
//        proxyInvocationHandle.setTarget(host);
//        Rent proxy = (Rent) proxyInvocationHandle.getProxy();
//        proxy.rent();
        //真实角色
        UserImpl user = new User();
        //代理实例的调用处理程序
        ProxyInvocationHandle proxyInvocationHandle = new ProxyInvocationHandle();
        proxyInvocationHandle.setTarget(user);//将真实角色放置进去！
        UserImpl proxy = (UserImpl) proxyInvocationHandle.getProxy();//动态生成对应的代理类！
        proxy.delete();

    }
}