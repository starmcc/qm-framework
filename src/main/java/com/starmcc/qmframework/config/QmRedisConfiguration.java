package com.starmcc.qmframework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Jedis缓存配置
 *
 * @author starmcc
 * @date 2021/11/19
 */
@ConfigurationProperties("qmframework.redis")
public class QmRedisConfiguration {

    /**
     * 主机ip
     */
    private String host = "127.0.0.1";
    /**
     * 端口
     */
    private int port = 6379;
    /**
     * 登录口令
     */
    private String password = null;
    /**
     * 连接超时和读写超时（单位ms）
     * 读写超时即：redis对该命令执行时间太长，超过设定时间后就放弃本次请求
     */
    private int timeOut = 1000;
    /**
     * 最大连接数
     */
    private int maxTotal = 10000;
    /**
     * 最多空闲数
     */
    private int maxIdle = 50;

    /**
     * 当池中没有连接时，最多等待5秒
     */
    private long maxWaitMillis = 5000L;

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }
}
