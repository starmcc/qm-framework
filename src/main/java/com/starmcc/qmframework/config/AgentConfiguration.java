package com.starmcc.qmframework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author starmcc
 * @version 2020/9/22 16:35
 * AOP 配置
 */
@ConfigurationProperties("qmframework.agent")
public class AgentConfiguration {

    /**
     * 是否打印日志
     */
    private static boolean log;

    @Value("${log:true}")
    public void setLog(boolean log) {
        AgentConfiguration.log = log;
    }

    public static boolean isLog() {
        return log;
    }
}
