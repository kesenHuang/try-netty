package com.kesen.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @className: com.kesen.netty.http-> TestHttpServerHandler
 * @description: http处理器
 * @author: kesen
 * @createDate: 2021-10-28 14:14
 * @version: 1.0
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {


        System.out.println("对应的channel=" + channelHandlerContext.channel()
                + " pipeline=" + channelHandlerContext.pipeline()
                + " 通过pipeline获取channel" + channelHandlerContext.pipeline().channel());

        System.out.println("当前ctx的handler=" + channelHandlerContext.handler());


        if (httpObject instanceof HttpRequest) {
            System.out.println("ctx 类型=" + channelHandlerContext.getClass());

            System.out.println("pipeline hashcode" + channelHandlerContext.pipeline().hashCode() + " TestHttpServerHandler hash=" + this.hashCode());

            System.out.println("msg 类型=" + httpObject.getClass());
            System.out.println("客户端地址" + channelHandlerContext.channel().remoteAddress());


            //获取到
            HttpRequest httpRequest = (HttpRequest) httpObject;
            //获取uri, 过滤指定的资源
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了 favicon.ico, 不做响应");
                return;
            }
            //回复信息给浏览器 [http协议]

            ByteBuf content = Unpooled.copiedBuffer("hello, 我是服务器", CharsetUtil.UTF_8);

            //构造一个http的相应，即 httpResponse
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_ENCODING, "UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            //将构建好 response返回
            channelHandlerContext.writeAndFlush(response);

        }
    }
}
