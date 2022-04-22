package com.gy.gyrpc.transport;

/**
 * 1、启动、监听
 * 2、接受请求
 * 3、关闭监听
 */
public interface TransportServer {
    void init(int port, RequestHandler requestHandler);

    void start();

    void stop();
}