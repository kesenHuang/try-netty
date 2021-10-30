package com.kesen.netty.im;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @className: com.kesen.netty.im-> GroupChatClientHandler
 * @description: 群聊客户端处理器
 * @author: kesen
 * @createDate: 2021-10-28 16:39
 * @version: 1.0
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println(msg.trim());
    }
}
