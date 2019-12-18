package com.hhqk.common.netty.nio.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NioServerHandler extends SimpleChannelInboundHandler<Object> {

//    private static Logger logger = Logger.getLogger(TestLog.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接收消息
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
        System.out.println("接收到客户端的数据:" + message);

        // 对客户端进行响应
        String serverMessage = "server msg";
        byte[] serverBytes = serverMessage.getBytes();

        ByteBuf serverBuf = Unpooled.buffer(serverBytes.length);
        serverBuf.writeBytes(serverBytes);
        ctx.writeAndFlush(serverBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--> channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--> handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--> channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--> channelUnregistered");
        super.channelUnregistered(ctx);
    }
}
