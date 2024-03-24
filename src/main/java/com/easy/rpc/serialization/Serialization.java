package com.easy.rpc.serialization;

import java.io.IOException;

public interface Serialization {
    //序列化方法
    <T> byte[] serialize(T obj) throws IOException;

    //反序列化
    <T> T deserialize(byte[] data,Class<T> clz) throws IOException;
}
