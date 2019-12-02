package com.hhqk.common.nio.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestNio {

    public static void main(String[] args) {
        //
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new NioServerInitializer());  //自定义

            ChannelFuture channelFuture = serverBootstrap.bind(8899);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
