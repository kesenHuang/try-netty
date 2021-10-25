package com.kesen.netty.bio.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @program: try-netty
 * @ClassName TimeServerHandler
 * @description:
 * @author: kesen
 * @create: 2021-10-23 11:07
 * @Version 1.0
 **/
public class TimeServerHandler implements Runnable {
    private Socket serverSocket;

    public TimeServerHandler(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    this.serverSocket.getInputStream()));
            out = new PrintWriter(this.serverSocket.getOutputStream(), true);
            String currentTime;
            String body;
            // 按行获取
            while (true) {
                body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("The time server receive order : " + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                        ?
                        new java.util.Date(System.currentTimeMillis()).toString()
                        :
                        "BAD ORDER";
                out.println(currentTime);
            }

        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (this.serverSocket != null) {
                try {
                    this.serverSocket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.serverSocket = null;
            }
        }
    }
}
