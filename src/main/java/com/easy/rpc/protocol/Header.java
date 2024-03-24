package com.easy.rpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义协议的消息头
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Header {

    private short magic; // 魔数

    private byte version; // 协议版本

    private byte extraInfo; // 附加信息

    private Long messageId; // 消息ID

    private Integer size; // 消息体长度


    public Header(short magic, byte version) {
        this.magic = magic;
        this.version = version;
        this.extraInfo = 0;
    }
}