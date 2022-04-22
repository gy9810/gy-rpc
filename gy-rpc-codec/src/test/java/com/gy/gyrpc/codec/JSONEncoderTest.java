package com.gy.gyrpc.codec;

import junit.framework.TestCase;

public class JSONEncoderTest extends TestCase {

    public void testEncode() {
        Encoder encoder = new JSONEncoder();

        TestBean bean = new TestBean();
        bean.setName("gy666");
        bean.setAge(20);

        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);
    }
}