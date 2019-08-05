package com.ming.thrift.demo;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author zhagnying
 * @description
 * @date 2019/8/5
 */
public class HelloServer {
    /**
     * thrift 服务端包含4个部分
     * transport层：经过封装的socket层
     * protocol层：网络读写的协议层，主要考虑的是数据的编解码与序列化反序列化等，为Processor提供输入输出流对象
     * Processor层：Processor封装了从输入流读数据及写数据到输出流的能力
     * server层：相当于一个组装员，把transport、protocol、Processor三者组装起来称为一个整体，对外提供服务
     *           同时，其自身包含了服务端的业务处理框架，如线程池模型等
     */
    public static void main(String[] args) throws TTransportException {
        //thrift 封装的 socket层， 使用端口7911 构建一个分阻塞的socket
        TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(7911);

        //thrift 协议层， 这里使用的是二进制协议
        TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();

        //thrift idl 接口的实现类
        HelloServiceImple helloServiceImple = new HelloServiceImple();

        //thrift Processor 业务逻辑处理层
        TProcessor processor = new HelloService.Processor<HelloServiceImple>(helloServiceImple);

        THsHaServer.Args rpcArgs = new THsHaServer.Args(serverTransport);
        rpcArgs.processor(processor);
        rpcArgs.protocolFactory(proFactory);

        //thrift server
        TServer server = new THsHaServer(rpcArgs);
        System.out.println("server begin...");
        server.serve();
    }
}
