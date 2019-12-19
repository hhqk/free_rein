package com.hhqk.common.netty.nio.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NioServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 利用编/解码器进行解决TCP粘包/拆包导致的半包读写问题
        // 使用Protobuf编解码框架进行java序列化
        System.out.println("server ChannelInitializer initChannel");
        ChannelPipeline pipeline = ch.pipeline();

//        pipeline.addLast("lengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//        pipeline.addLast("lengthFieldPrepender", new LengthFieldPrepender(4));
//        pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
//        pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));

        pipeline.addLast("myServerHandler", new NioServerHandler());  //自定义
    }
}
