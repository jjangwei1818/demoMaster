package com.jw.demoMaster.util;


import io.netty.buffer.ByteBuf;

/**
 * 通用工具类
 * created on 2017/11/6
 *
 * @author guest
 */
public class CommonUtils {

    private final static String POINT = ".";

    /**
     * 字节转ip地址
     *
     * @param src 字节buf
     * @return String address
     */
    public static String bytesToAddress(ByteBuf src) {
        return String.valueOf(src.readUnsignedByte()) +
                POINT +
                src.readUnsignedByte() +
                POINT +
                src.readUnsignedByte() +
                POINT +
                src.readUnsignedByte();
    }

    /**
     * 字节转标签id数组
     *
     * @param src 字节buf
     * @return int[] ids
     */
    public static int[] bytesToTags(ByteBuf src) {
        int count = src.readableBytes() / 4;
        int[] tags = new int[count];
        for (int i = 0; i < count; i++) {
            tags[i] = src.readIntLE();
        }
        return tags;
    }

    /**
     * 4字节小端16进制转成正确显示
     *
     * @param src 源str
     * @return 正确str
     */
    public static String littleEndianToHexStr(String src) {
        String sb = String.valueOf(src.charAt(6)) +
                src.charAt(7) +
                src.charAt(4) +
                src.charAt(5) +
                src.charAt(2) +
                src.charAt(3) +
                src.charAt(0) +
                src.charAt(1);
        return sb;
    }
}

