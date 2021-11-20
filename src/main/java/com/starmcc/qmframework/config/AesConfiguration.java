package com.starmcc.qmframework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * aes配置
 *
 * @author starmcc
 * @date 2021/11/20
 */
@ConfigurationProperties("qmframework.aes")
public class AesConfiguration {

    private static boolean start;
    private static String key;
    private static String encoding;
    private static int number;

    @Value("${start:false}")
    public void setStart(boolean start) {
        AesConfiguration.start = start;
    }

    @Value("${key:ohiah2020sDShdieub51h8910s}")
    public void setKey(String key) {
        AesConfiguration.key = key;
    }

    @Value("${encoding:UTF-8}")
    public void setEncoding(String encoding) {
        AesConfiguration.encoding = encoding;
    }

    @Value("${number:1}")
    public void setNumber(int number) {
        AesConfiguration.number = number;
    }

    public static boolean isStart() {
        return start;
    }

    public static String getKey() {
        return key;
    }

    public static String getEncoding() {
        return encoding;
    }

    public static int getNumber() {
        return number;
    }
}