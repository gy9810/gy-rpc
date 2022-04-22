package com.gy.gyrpc.example;

import com.gy.gyrpc.client.RpcClient;

public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);

        int r1 = service.add(4,9);
        int r2 = service.minus(15,8);
        System.out.println(r1);
        System.out.println(r2);
    }
}
