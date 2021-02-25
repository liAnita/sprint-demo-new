package com.demo01.proxy;

import com.demo01.pojo.Host;
import com.demo01.pojo.Rent;

public class MyProxy implements Rent {
    private Host host;

    public MyProxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        watchHouse();
        host.rent();
        fare();
    }

    public void watchHouse(){
        System.out.println("中介带你看房");
    }

    public void fare(){
        System.out.println("收取中介费");
    }

}
