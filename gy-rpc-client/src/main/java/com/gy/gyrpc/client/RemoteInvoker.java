package com.gy.gyrpc.client;

import com.gy.gyrpc.Request;
import com.gy.gyrpc.Response;
import com.gy.gyrpc.ServiceDescriptor;
import com.gy.gyrpc.codec.Decoder;
import com.gy.gyrpc.codec.Encoder;
import com.gy.gyrpc.transport.HttpTransportClient;
import com.gy.gyrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务的代理类
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector){
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        Response response = invokeRemote(request);
        if(response == null || response.getCode() != 0){
            throw new IllegalStateException("远程调用失败：" + response);
        }
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        Response response = new Response();
        TransportClient client = new HttpTransportClient();
        try {
            client = selector.select();

            byte[] outBytes = encoder.encode(request);
            InputStream resp = client.write(new ByteArrayInputStream(outBytes));
            byte[]  inBytes = IOUtils.readFully(resp, resp.available());
            response = decoder.decode(inBytes, Response.class);

        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            response.setCode(1);
            response.setMessage("RpcClient got error: " + e.getClass() + " : " + e.getMessage());
        } finally {
            if(client != null){
                selector.release(client);
            }
        }
        return response;
    }
}
