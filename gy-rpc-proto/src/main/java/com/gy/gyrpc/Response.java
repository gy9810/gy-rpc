package com.gy.gyrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示RPC的一个响应
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int code = 0; // 服务返回编码，0表示成功，1表示失败
    private String message = "succeed!"; // 具体的返回消息
    private Object data; // 返回的数据
}
