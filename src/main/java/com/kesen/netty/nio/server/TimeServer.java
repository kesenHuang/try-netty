package com.kesen.netty.nio.server;

/**
 * @className: com.kesen.netty.nio.server-> TimeServer
 * @description: NIO时间服务器
 * @author: kesen
 * @createDate: 2021-10-26 17:40
 * @version: 1.0
 */
public class TimeServer {

    public static void main(String[] args) {
        int port= 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
