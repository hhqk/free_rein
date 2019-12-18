package com.hhqk.common.netty.nio.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NioClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 接受消息
        System.out.println(msg);
        ctx.writeAndFlush("client msg");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 第一次建立连接客户端向服务端发送数据
        System.out.println("--> client channelActive");
        ctx.writeAndFlush("channelActive client msg");
    }
}
