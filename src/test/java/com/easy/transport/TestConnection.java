package com.easy.transport;

import com.easy.rpc.transport.EasyRpcClient;
import org.junit.Test;

public class TestConnection {
    @Test
    public void testSendMessage() {
        EasyRpcClient client = new EasyRpcClient("localhost", 8889);
        client.sendRequest("hello").addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("请求发送成功！");
            } else {
                System.err.println("请求发送失败：" + future.cause().getMessage());
            }
        });
    }
}
