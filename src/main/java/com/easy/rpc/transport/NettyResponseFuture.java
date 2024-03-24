package com.easy.rpc.transport;

import com.easy.rpc.protocol.Message;
import io.netty.channel.Channel;
import io.netty.util.concurrent.Promise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NettyResponseFuture<T> {
    private long createTime;
    private long timeOut;
    private Message request;
    private Channel channel;
    private Promise<T> promise;
}
