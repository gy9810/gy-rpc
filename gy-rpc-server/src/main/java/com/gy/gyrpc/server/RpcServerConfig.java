package com.gy.gyrpc.server;

import com.gy.gyrpc.codec.Decoder;
import com.gy.gyrpc.codec.Encoder;
import com.gy.gyrpc.codec.JSONDecoder;
import com.gy.gyrpc.codec.JSONEncoder;
import com.gy.gyrpc.transport.HttpTransportServer;
import com.gy.gyrpc.transport.TransportServer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * server配置
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HttpTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;
}
