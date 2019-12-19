package com.hhqk.common.netty.nio.server;

public class TestServer {

    public static void main(String[] args) {
        new NioServer().init(8899);
    }
}
