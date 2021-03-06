package com.starmcc.qmframework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 特殊请求配置
 *
 * @Author starmcc
 * @Date 2019/8/30 14:49
 */
@ConfigurationProperties(prefix = "qmframework.special", ignoreUnknownFields = false)
public class SpecialConfiguration {

    public static String[] uri;

    public String[] getUri() {
        return SpecialConfiguration.uri;
    }

    @Value("${uri:}")
    public void setUri(String[] uri) {
        SpecialConfiguration.uri = uri;
    }
}
