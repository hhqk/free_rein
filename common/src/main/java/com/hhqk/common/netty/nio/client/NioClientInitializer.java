package com.hhqk.common.netty.nio.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NioClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 利用编/解码器进行解决TCP粘包/拆包导致的半包读写问题
        System.out.println("client ChannelInitializer initChannel");
        ChannelPipeline pipeline = ch.pipeline();

//        pipeline.addLast("lengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//        pipeline.addLast("lengthFieldPrepender", new LengthFieldPrepender(4));
//        pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
//        pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));

        pipeline.addLast("myClientHandler", new NioClientHandler());
    }
}
