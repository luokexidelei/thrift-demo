package com.ming.thrift.demo;

import org.apache.thrift.TException;

/**
 * @author zhagnying
 * @description
 * @date 2019/8/5
 */
public class HelloServiceImple implements HelloService.Iface {
    public String helloWorld(String hello) throws TException {
        System.out.println("hello world " + hello);
        return "I'm server, hello";
    }
}
