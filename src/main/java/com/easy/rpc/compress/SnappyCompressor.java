package com.easy.rpc.compress;

import org.xerial.snappy.Snappy;

import java.io.IOException;

public class SnappyCompressor implements Compressor{
    @Override
    public byte[] compress(byte[] array) throws IOException {
        if(array == null) {
            return new byte[0];
        }
        return Snappy.compress(array);
    }

    @Override
    public byte[] unCompress(byte[] array) throws IOException {
        if(array == null) {
            return new byte[0];
        }
        return Snappy.uncompress(array);
    }
}
