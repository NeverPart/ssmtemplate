package com.ch.mytemplate.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @Author ch
 * @Description Base64工具
 * @Date 2021/6/30 10:27
 **/
public class Base64ConvertUtil {

    private Base64ConvertUtil() {
    }

    /**
     * 加密JDK1.8
     *
     * @param str
     * @return java.lang.String
     * @Author ch
     * @Date 2021/6/30 10:27
     */
    public static String encode(String str) throws UnsupportedEncodingException {
        byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes("utf-8"));
        return new String(encodeBytes);
    }

    /**
     * 解密JDK1.8
     *
     * @param str
     * @return java.lang.String
     * @Author ch
     * @Date 2021/6/30 10:27
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        byte[] decodeBytes = Base64.getDecoder().decode(str.getBytes("utf-8"));
        return new String(decodeBytes);
    }

}
