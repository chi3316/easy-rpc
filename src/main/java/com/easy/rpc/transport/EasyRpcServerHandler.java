package com.easy.rpc.transport;

import com.easy.rpc.constant.Constants;
import com.easy.rpc.protocol.Message;
import com.easy.rpc.protocol.Request;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EasyRpcServerHandler extends SimpleChannelInboundHandler<Message<Request>> {
    // 业务线程池
    private static Executor executor = Executors.newCachedThreadPool();
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message<Request> requestMessage) throws Exception {
        byte extraInfo = requestMessage.getHeader().getExtraInfo();
        if (Constants.isHeartBeat(extraInfo)) { // 心跳消息，直接返回即可
            channelHandlerContext.writeAndFlush(requestMessage);
            return;
        }
        // 非心跳消息，直接封装成Runnable提交到业务线程池
        executor.execute(new InvokeRunnable(requestMessage, channelHandlerContext));
    }
}
