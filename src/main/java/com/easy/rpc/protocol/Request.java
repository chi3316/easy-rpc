package com.easy.rpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 请求消息的消息体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request implements Serializable {
    private String serviceName; // 请求的Service类名

    private String methodName; // 请求的方法名称

    private Class[] argTypes; // 请求方法的参数类型

    private Object[] args; // 请求方法的参数

    public Request(String serviceName, String methodName, Object[] args) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.args = args;
    }
}
