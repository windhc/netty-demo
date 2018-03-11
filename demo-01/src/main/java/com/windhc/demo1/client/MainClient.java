package com.windhc.demo1.client;

/**
 * @author windhc
 */
public class MainClient {

    public static void main(String[] args) {
        System.out.println("This is NettyClient Main Method ");

        MyClientBootstrap.connectServer("127.0.0.1", 9000);
    }
}
