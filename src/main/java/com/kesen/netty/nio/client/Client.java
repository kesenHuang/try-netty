package com.kesen.netty.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @className: com.kesen.netty.nio.client-> Client
 * @description: NIO 客户端
 * @author: kesen
 * @createDate: 2021-10-25 15:41
 * @version: 1.0
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888));

        String newData = "New String to write to file..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.clear();
        buf.put(newData.getBytes());

        // 反转之后，就可以读取buf数据
        buf.flip();
        while(buf.hasRemaining()) {

            //注意SocketChannel.write()方法的调用是在一个while循环中的。Write()方法无法保证能写多少字节到SocketChannel。
            // 所以，我们重复调用write()直到Buffer没有要写的字节为止。
            socketChannel.write(buf);
        }
        Thread.sleep(10000);
    }
}
