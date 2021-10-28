package com.kesen.io.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @className: com.kesen.netty.nio-> Server
 * @description: NIO 服务器
 * @author: kesen
 * @createDate: 2021-10-25 15:14
 * @version: 1.0
 */
public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel;

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));
        serverSocketChannel.configureBlocking(false);
        while (true) {
            SocketChannel socketChannel =
                    serverSocketChannel.accept();

            if (socketChannel != null) {
                //do something with socketChannel...
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int length;
                while (true) {
                    try {
                        if ((length = socketChannel.read(byteBuffer)) == -1) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                    byteBuffer.flip();
                    System.out.println("length:" + length);
                    while (byteBuffer.hasRemaining()) {
                        System.out.print((char) byteBuffer.get());
                    }
                    System.out.print("\r\n");
                    byteBuffer.clear();

                }
            }

        }


    }
}
