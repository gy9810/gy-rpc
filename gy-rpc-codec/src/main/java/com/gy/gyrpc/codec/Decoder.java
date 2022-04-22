package com.gy.gyrpc.codec;

/**
 * 反序列化
 */
public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}
