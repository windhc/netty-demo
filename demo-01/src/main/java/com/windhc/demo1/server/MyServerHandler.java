package com.windhc.demo1.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author windhc
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 服务器和客户端连接通道激活
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务器和客户端连接通道激活");
        super.channelActive(ctx);
    }

    /**
     * 服务器和客户端连接通道失效
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    /**
     * 服务器收到客户端的消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
