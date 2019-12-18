package com.hhqk.common.netty.nio.client;

public class TestClient {

    public static void main(String[] args) throws Exception {

        new NioClient().connect("localhost", 8899);
    }
}
