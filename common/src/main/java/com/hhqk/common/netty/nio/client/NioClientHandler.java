package com.hhqk.common.netty.nio.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NioClientHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接受消息
        System.out.println(msg);
        // 数据流缓冲区
        ByteBuf buf = (ByteBuf) msg;
        // 数据流缓冲区的长度
        int length = buf.readableBytes();
        //
        byte[] bytes = new byte[length];
        // 从缓冲区读取数据到数组
        buf.readBytes(bytes);
        //
        String message = new String(bytes, "UTF-8");
        System.out.println("接收到服务端的数据:" + message);

        // 对服务端进行响应
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
        String message = "channelActive client msg";
        byte[] bytes = message.getBytes();

        ByteBuf buf = Unpooled.buffer(bytes.length);
        buf.writeBytes(bytes);

        // 因为在 服务端里 NioServerHandler 用Object接收转换类型为 ByteBuf 的
        // 所以这里客户端向服务端发送数据时也用 ByteBuf
        ctx.writeAndFlush(buf);
    }
}
