package com.hhqk.common.netty.nio.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NioClient {

    /**
     * channel 通道 代表一个连接
     * channelHandler 通道处理器 请求的过滤器，拦截器
     * pipeline 管道  一个通道有多个管道
     * @param ip : localhost/127.0.0.1
     * @param port : 8080
     * @throws Exception
     */
    public void connect(String ip, int port) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new NioClientInitializer());

            // 发起连接操作
            ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
            // 等待客户端链路关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 释放资源
            group.shutdownGracefully();
        }

    }
}
