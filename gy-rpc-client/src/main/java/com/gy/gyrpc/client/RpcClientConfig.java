package com.gy.gyrpc.client;

import com.gy.gyrpc.Peer;
import com.gy.gyrpc.codec.Decoder;
import com.gy.gyrpc.codec.Encoder;
import com.gy.gyrpc.codec.JSONDecoder;
import com.gy.gyrpc.codec.JSONEncoder;
import com.gy.gyrpc.transport.HttpTransportClient;
import com.gy.gyrpc.transport.TransportClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectionCount = 1;
    private List<Peer> servers = Arrays.asList(new Peer("127.0.0.1", 3000));
}
