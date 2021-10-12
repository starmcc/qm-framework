package com.starmcc.qmframework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author starmcc
 * @version 2019/8/29 17:21
 * 请求配置
 */
@ConfigurationProperties(prefix = "qmframework.transmit", ignoreUnknownFields = false)
public class TransmitConfiguration {

    public static String requestKey;
    public static String responseKey;
    public static String responseMessageLang;

    @Value("${request.key:}")
    public void setRequestKey(String requestKey) {
        TransmitConfiguration.requestKey = requestKey;
    }

    @Value("${response-key:}")
    public void setResponseKey(String responseKey) {
        TransmitConfiguration.responseKey = responseKey;
    }

    @Value("${response-message-lang:CN}")
    public void setResponseMessageLang(String responseMessageLang) {
        TransmitConfiguration.responseMessageLang = responseMessageLang;
    }

    public String getRequestKey() {
        return TransmitConfiguration.requestKey;
    }

    public String getResponseKey() {
        return TransmitConfiguration.responseKey;
    }

    public String getResponseMessageLang() {
        return TransmitConfiguration.responseMessageLang;
    }
}
