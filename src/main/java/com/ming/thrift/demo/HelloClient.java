package com.ming.thrift.demo;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author zhagnying
 * @description
 * @date 2019/8/5
 */
public class HelloClient {
    /**
     * client的编写也比较简单，与传统的 socket 通信代码的编写没有太多的差异
     *
     * thrift基本上把通信的细节已经封装好
     * 不管是server还是client ，要进行网络通信，依然还是创建连接，从输入流读数据，写数据到输出流
     * 通信的原理及流程都是一致的
     */
    public static void main(String[] args) throws TException {
        //构建socket
        TTransport transport = new TFramedTransport(new TSocket("localhost", 7911));
        //链接到server
        transport.open();
        //创建读写协议对象
        TProtocol protocol = new TBinaryProtocol(transport);
        //这里的client为thrift编译生成的代码 HelloService里的client类
        HelloService.Client client = new HelloService.Client(protocol);
        //调用helloWorld方法，整个 rpc调用过程 由thrift处理， feel so easy ？
        String res = client.helloWorld("I'm client");
        System.out.println(res);
    }
}
