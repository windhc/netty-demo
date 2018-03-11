package com.windhc.demo1.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.LineEncoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author windhc
 */
public class MyClientBootstrap {

    /**
     * 对外提供的开启Netty服务器程序的方式
     * @param port
     */
//    public static void startServer(int port) {
//        bind(port);
//    }

    /**
     * 内部开启服务器的框架
     * @param port
     */
    private static void connect(String ip, int port) {

        // 配置客户端的NIO线程组
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        // 创建Bootstrap引导程序
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup);
        bootstrap.channel(NioSocketChannel.class);

        bootstrap.remoteAddress(ip, port);

        // 初始化我们的channel
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                // 添加消息解码
                ChannelPipeline pipeline = ch.pipeline();
                // 我们发出去的消息是行编码的
                pipeline.addLast(new LineEncoder());
                // 我们服务端收到的消息是基于字符串编码
                pipeline.addLast(new StringDecoder());

                // 把我们的handler添加进去
                pipeline.addLast(new MyClientHandler());
            }
        });

        // 让客户端和服务器端连接上
        ChannelFuture future = bootstrap.connect(ip, port);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("Client connect success, Server-ip: " + ip);
                } else {
                    System.out.println("Client connect failure");
                }
            }
        });
    }

    public static void connectServer(String ip, int port) {
        connect(ip, port);
    }
}
