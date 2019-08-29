package com.starmcc.qmframework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 版本配置
 *
 * @Author: qm
 * @Date: 2019/8/29 17:49
 */
@ConfigurationProperties(prefix = "qmframework.version", ignoreUnknownFields = false)
public class VersionConfiguration {

    public static boolean start;
    public static String now;
    public static String[] allows;

    @Value("${start:false}")
    public void setStart(boolean start) {
        VersionConfiguration.start = start;
    }

    @Value("${now:1.0.0}")
    public void setNow(String now) {
        VersionConfiguration.now = now;
    }

    @Value("${allows:}")
    public void setAllows(String[] allows) {
        VersionConfiguration.allows = allows;
    }

    public boolean isStart() {
        return VersionConfiguration.start;
    }

    public String getNow() {
        return VersionConfiguration.now;
    }

    public String[] getAllows() {
        return VersionConfiguration.allows;
    }
}
