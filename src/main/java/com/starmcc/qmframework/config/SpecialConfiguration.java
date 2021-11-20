package com.starmcc.qmframework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author starmcc
 * @version 2019/8/30 14:49
 * 特殊请求配置
 */
@ConfigurationProperties("qmframework.special")
public class SpecialConfiguration {

    private static String[] uri;

    @Value("${uri:}")
    public void setUri(String[] uri) {
        SpecialConfiguration.uri = uri;
    }

    public static String[] getUri() {
        return uri;
    }
}
