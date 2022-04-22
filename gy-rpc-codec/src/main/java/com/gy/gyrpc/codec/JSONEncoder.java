package com.gy.gyrpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 基于JSON的序列化
 */
public class JSONEncoder implements Encoder{

    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
