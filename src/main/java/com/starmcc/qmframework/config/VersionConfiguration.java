package com.starmcc.qmframework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author starmcc
 * @version 2019/8/29 17:49
 * 版本配置
 */
@ConfigurationProperties("qmframework.version")
public class VersionConfiguration {

    private static boolean start;
    private static String now;
    private static String[] allows;

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

    public static boolean isStart() {
        return start;
    }

    public static String getNow() {
        return now;
    }

    public static String[] getAllows() {
        return allows;
    }
}
