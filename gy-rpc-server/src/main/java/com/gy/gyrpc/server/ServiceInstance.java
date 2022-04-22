package com.gy.gyrpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 表示一个具体的服务
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInstance {
    private Object target; // 服务由哪个对象提供
    private Method method; // 哪个方法暴露成为一个服务
}
