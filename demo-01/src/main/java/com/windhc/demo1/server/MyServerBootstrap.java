package com.windhc.demo1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.LineEncoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author windhc
 */
public class MyServerBootstrap {

    /**
     * 对外提供的开启Netty服务器程序的方式
     * @param port
     */
    public static void startServer(int port) {
        bind(port);
    }

    /**
     * 内部开启服务器的框架
     * @param port
     */
    private static void bind(int port) {

        // 配置服务端的NIO线程组
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        // 创建ServerBootstrap引导程序
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker);
        serverBootstrap.channel(NioServerSocketChannel.class);

        // 初始化我们的channel
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                // 添加消息解码
                ChannelPipeline pipeline = ch.pipeline();
                // 我们发出去的消息是行编码的
                pipeline.addLast(new LineEncoder());
                // 我们服务端收到的消息是基于字符串编码
                pipeline.addLast(new StringDecoder());

                // 把我们的handler添加进去
                pipeline.addLast(new MyServerHandler());
            }
        });

        try {
            ChannelFuture future = serverBootstrap.bind(port).sync();
            if (future.isSuccess()) {
                System.out.println("Server start success, port: " + port);
            } else {
                System.out.println("Server start failure");
            }
        } catch (InterruptedException e) {
            System.out.println("Server start exception");
            e.printStackTrace();
        }
    }
}
