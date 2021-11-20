package com.starmcc.qmframework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author starmcc
 * @version 2019/8/29 17:21
 * 请求配置
 */
@ConfigurationProperties("qmframework.transmit")
public class TransmitConfiguration {

    private static String requestKey;
    private static String responseKey;
    private static String responseMessageLang;

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

    public static String getRequestKey() {
        return TransmitConfiguration.requestKey;
    }

    public static String getResponseKey() {
        return TransmitConfiguration.responseKey;
    }

    public static String getResponseMessageLang() {
        return TransmitConfiguration.responseMessageLang;
    }
}
