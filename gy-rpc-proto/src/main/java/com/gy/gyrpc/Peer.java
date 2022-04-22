package com.gy.gyrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示网络传输的一个端点
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Peer {
    private String host;
    private int port;
}
