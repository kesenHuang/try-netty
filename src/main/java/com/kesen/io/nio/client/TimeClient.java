package com.kesen.io.nio.client;

/**
 * @className: com.kesen.netty.nio.client-> TimeClient
 * @description: 请求时间客户端
 * @author: kesen
 * @createDate: 2021-10-26 17:41
 * @version: 1.0
 */
public class TimeClient {
    /**
     * @param args
     */
    public static void main(String[] args) {

        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }
        new Thread(new TimeClientHandle("127.0.0.1", port), "TimeClient-001")
                .start();
    }
}
