package com.easy.rpc.transport;

import com.easy.rpc.coder.EasyRpcDecoder;
import com.easy.rpc.coder.EasyRpcEncoder;
import com.easy.rpc.constant.Constants;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.Closeable;

public class EasyRpcClient implements Closeable {

    protected Bootstrap clientBootstrap;
    protected EventLoopGroup group;
    private String host;
    private int port;

    public EasyRpcClient(String host, int port) {
        this.host = host;
        this.port = port;
        // 创建并配置客户端Bootstrap
        clientBootstrap = new Bootstrap();
        group = NettyEventLoopFactory.eventLoopGroup(Constants.DEFAULT_IO_THREADS, "NettyClientWorker");
        clientBootstrap.group(group)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .channel(NioSocketChannel.class) // 创建的Channel类型
                // 指定ChannelHandler的顺序
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("demo-rpc-encoder", new EasyRpcEncoder());
                        ch.pipeline().addLast("demo-rpc-decoder", new EasyRpcDecoder());
                        ch.pipeline().addLast("client-handler", new EasyRpcClientHandler());
                    }
                });
    }


    public ChannelFuture connect() {
        // 连接指定的地址和端口
        ChannelFuture connect = clientBootstrap.connect(host, port);
        connect.awaitUninterruptibly();
        return connect;
    }

    /**
     * 发送请求
     * @param request
     * @return
     */
    public ChannelFuture sendRequest(Object request) {
        Channel channel = connect().channel(); // 获取已连接的Channel

        // 发送请求并等待响应
        ChannelFuture writeFuture = channel.writeAndFlush(request);

        // 添加监听器来处理响应或错误
        writeFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                // 请求发送成功
                // 这里可以添加代码处理响应，例如打印或解析响应
            } else {
                // 请求发送失败
                future.cause().printStackTrace(); // 打印异常堆栈信息
            }
        });

        return writeFuture;
    }

    @Override
    public void close() {
        group.shutdownGracefully();
    }
}
