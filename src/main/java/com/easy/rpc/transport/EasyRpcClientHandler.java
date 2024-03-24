package com.easy.rpc.transport;

import com.easy.rpc.constant.Constants;
import com.easy.rpc.protocol.Message;
import com.easy.rpc.protocol.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EasyRpcClientHandler extends SimpleChannelInboundHandler<Message<Response>> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message<Response> responseMessage) throws Exception {
        NettyResponseFuture responseFuture =
                Connection.IN_FLIGHT_REQUEST_MAP.remove(responseMessage.getHeader().getMessageId());
        Response response = responseMessage.getContent();
        // 心跳消息特殊处理
        if (response == null && Constants.isHeartBeat(responseMessage.getHeader().getExtraInfo())) {
            response = new Response();
            response.setCode(Constants.HEARTBEAT_CODE);
        }
        responseFuture.getPromise().setSuccess(response.getResult());
    }
}
