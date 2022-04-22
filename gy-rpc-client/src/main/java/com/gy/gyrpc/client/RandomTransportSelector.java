package com.gy.gyrpc.client;

import ch.qos.logback.core.net.server.Client;
import com.gy.gyrpc.Peer;
import com.gy.gyrpc.common.utils.ReflectionUtils;
import com.gy.gyrpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class RandomTransportSelector implements TransportSelector{
    List<TransportClient> clients;

    public RandomTransportSelector(){
        clients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for(Peer peer : peers){
            for(int i = 0; i < count; i++){
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("连接服务器：{}", peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int randomIdx = new Random().nextInt(clients.size());
        return clients.remove(randomIdx);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        for(TransportClient client : clients){
            client.close();
        }
        clients.clear();
    }
}
