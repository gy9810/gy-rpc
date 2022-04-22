package com.gy.gyrpc.server;

import com.gy.gyrpc.Request;
import com.gy.gyrpc.ServiceDescriptor;
import com.gy.gyrpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理RPC暴露的服务
 * 1、注册服务
 * 2、查找服务
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager(){
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean){
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for(Method method : methods){
            ServiceDescriptor serDes = ServiceDescriptor.from(interfaceClass, method);
            ServiceInstance serIns = new ServiceInstance(bean, method);

            services.put(serDes, serIns);
            log.info("注册服务类名：{} 方法名：{}", serDes.getClazz(), serDes.getMethod());
        }
    }

    public ServiceInstance lookup(Request request){
        ServiceDescriptor serDes = request.getService();
        return services.get(serDes);
    }
}
