package com.gy.gyrpc.server;

import com.gy.gyrpc.Request;
import com.gy.gyrpc.ServiceDescriptor;
import com.gy.gyrpc.common.utils.ReflectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class ServiceManagerTest{
    ServiceManager sm;

    @Before
    public void init(){
        sm = new ServiceManager();
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void testRegister() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void testLookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor serDes = ServiceDescriptor.from(TestInterface.class, method);

        Request request = new Request();
        request.setService(serDes);
        ServiceInstance serIns = sm.lookup(request);
        Assert.assertNotNull(serIns);
    }
}