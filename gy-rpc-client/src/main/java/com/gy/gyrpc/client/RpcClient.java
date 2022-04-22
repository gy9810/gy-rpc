package com.gy.gyrpc.client;

import com.gy.gyrpc.codec.Decoder;
import com.gy.gyrpc.codec.Encoder;
import com.gy.gyrpc.common.utils.ReflectionUtils;
import com.gy.gyrpc.transport.TransportClient;

import java.lang.reflect.Proxy;

public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient(){
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config){
        this.config = config;

        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        this.selector = ReflectionUtils.newInstance(config.getSelectorClass());

        this.selector.init(config.getServers(), config.getConnectionCount(), config.getTransportClass());
    }

    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz, encoder, decoder, selector));
    }
}
