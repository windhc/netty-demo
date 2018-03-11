package com.windhc.demo1.server;

/**
 * @author windhc
 */
public class MainServer {

    public static void main(String[] args) {
        System.out.println("This is NettyServer Main Method ");

        MyServerBootstrap.startServer(9000);
    }
}
