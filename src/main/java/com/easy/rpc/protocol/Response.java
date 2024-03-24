package com.easy.rpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应消息的消息体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response implements Serializable {
    private int code = 1; // 响应的错误码，正常响应为1，异常响应为0

    private String errMsg; // 异常信息

    private Object result; // 响应结果
}
