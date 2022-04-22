package com.gy.gyrpc.common.utils;

import junit.framework.TestCase;

import java.lang.reflect.Method;

public class ReflectionUtilsTest extends TestCase {

    public void testNewInstance() {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(t);
    }

    public void testGetPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1, methods.length);

        String mname = methods[0].getName();
        assertEquals("c", mname);
    }

    public void testInvoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        Method m = methods[0];

        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        Object str = ReflectionUtils.invoke(t, m);
        assertEquals("c", str);
    }
}