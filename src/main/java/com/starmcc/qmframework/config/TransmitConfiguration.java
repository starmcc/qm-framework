package com.starmcc.qmframework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 请求配置
 *
 * @Author: qm
 * @Date: 2019/8/29 17:21
 */
@ConfigurationProperties(prefix = "qmframework.transmit", ignoreUnknownFields = false)
public class TransmitConfiguration {

    public static String requestKey;
    public static String[] requestSpecialUri;
    public static String responseKey;
    public static String responseMessageLang;

    @Value("${request.key:value}")
    public void setRequestKey(String requestKey) {
        TransmitConfiguration.requestKey = requestKey;
    }

    @Value("${request-special-uri:}")
    public void setRequestSpecialUri(String[] requestSpecialUri) {
        TransmitConfiguration.requestSpecialUri = requestSpecialUri;
    }

    @Value("${response-key:value}")
    public void setResponseKey(String responseKey) {
        TransmitConfiguration.responseKey = responseKey;
    }

    @Value("${response-message-lang:value}")
    public void setResponseMessageLang(String responseMessageLang) {
        TransmitConfiguration.responseMessageLang = responseMessageLang;
    }

    public String getRequestKey() {
        return TransmitConfiguration.requestKey;
    }

    public String[] getRequestSpecialUri() {
        return TransmitConfiguration.requestSpecialUri;
    }

    public String getResponseKey() {
        return TransmitConfiguration.responseKey;
    }

    public String getResponseMessageLang() {
        return TransmitConfiguration.responseMessageLang;
    }
}