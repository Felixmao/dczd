package com.dczd.common.util;

import com.github.pagehelper.util.StringUtil;

/**
 * @program: com.dczd.common.util
 * @description: ConvertUtill 转换工具类
 * @author: hou yangkun
 * @create: 2018-11-29
 */
public class ConvertUtil {

    private final static byte[] hex = "0123456789ABCDEF".getBytes();


    private static int parse(char c) {
        if (c >= 'a') {
            return (c - 'a' + 10) & 0x0f;
        }
        if (c >= 'A') {
            return (c - 'A' + 10) & 0x0f;
        }
        return (c - '0') & 0x0f;
    }


    /**
     * 字节数组转换到十六进制字符串
     *
     * @param bytes {@link Byte}字节数组
     * @return {@link String} 十六进制字符串
     */
    public static String bytesToHexString(byte[] bytes) {
        byte[] buff = new byte[2 * bytes.length];
        for (int i = 0, length = bytes.length; i < length; i++) {
            buff[2 * i] = hex[(bytes[i] >> 4) & 0x0f];
            buff[2 * i + 1] = hex[bytes[i] & 0x0f];
        }
        return new String(buff);
    }


    /**
     * 十六进制字符串转换到字节数组
     *
     * @param hexstr {@link String} 十六进制字符串
     * @return {@link Byte}[]字节数组
     */
    public static byte[] hexStringToBytes(String hexstr) {
        // 判空
        if (StringUtil.isEmpty(hexstr)) {
            return null;
        }

        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0, length = b.length; i < length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }
}
