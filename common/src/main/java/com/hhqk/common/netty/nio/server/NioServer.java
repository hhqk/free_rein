package com.hhqk.common.netty.nio.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NioServer {

    public void init(int port) {
        //
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NioServerInitializer());  // 自定义I/O处理事件,记录日志，对消息进行编解码等

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync(); // 绑定监听端口
            channelFuture.channel().closeFuture().sync(); // 等待服务器监听端口关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
