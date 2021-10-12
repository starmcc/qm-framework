package com.starmcc.qmframework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author starmcc
 * @version 2019/8/29 17:52
 * AES配置
 */
@ConfigurationProperties(prefix = "qmframework.aes", ignoreUnknownFields = false)
public class AesConfiguration {

    public static boolean start;
    public static String key;
    public static String encoding;
    public static int number;

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

    public boolean isStart() {
        return AesConfiguration.start;
    }

    public String getKey() {
        return AesConfiguration.key;
    }

    public String getEncoding() {
        return AesConfiguration.encoding;
    }

    public int getNumber() {
        return AesConfiguration.number;
    }
}
