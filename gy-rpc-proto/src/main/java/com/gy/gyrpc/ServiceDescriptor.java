package com.gy.gyrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 表示调用服务的信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor serDes = new ServiceDescriptor();
        serDes.setClazz(clazz.getName());
        serDes.setMethod(method.getName());
        serDes.setReturnType(method.getReturnType().getName());
        Class[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for(int i = 0; i < parameterClasses.length; i++){
            parameterTypes[i] = parameterClasses[i].getName();
        }
        serDes.setParameterTypes(parameterTypes);
        return serDes;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        ServiceDescriptor that = (ServiceDescriptor) obj;
        return this.toString().equals(that.toString());
    }

    @Override
    public String toString() {
        return "class=" + clazz
                + ", method=" + method
                + ", returnType=" + returnType
                + ", parameterTypes=" + Arrays.toString(parameterTypes);
    }
}
